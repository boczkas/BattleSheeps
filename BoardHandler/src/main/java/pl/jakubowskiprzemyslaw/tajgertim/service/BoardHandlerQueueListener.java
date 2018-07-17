package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import pl.jakubowskiprzemyslaw.tajgertim.models.NoSuchPlayerException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoShipAtCoordinateException;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.OpponentBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.PlayerBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@BattleShipQueueInteractionHandler
public class BoardHandlerQueueListener {

    private final QueueService queueService;
    private final BoardHandler boardHandler;
    private final LoggerService logger;

    public BoardHandlerQueueListener(QueueService queueService, BoardHandler boardHandler, LoggerService logger) {
        this.queueService = queueService;
        this.boardHandler = boardHandler;
        this.logger = logger;
    }

    @RabbitListener(queues = "BoardHandlerShotQueryQueue")  // 12
    public void listenOnBoardHandlerShotQueryQueue(PlayerShootCoordinate playerShootCoordinate) throws NoSuchPlayerException, NoMastAtPositionException {

        logger.logInfo(BoardHandlerQueueListener.class, "Received message" + playerShootCoordinate);

        Player player = playerShootCoordinate.getPlayer();
        Coordinate shotCoordinate = playerShootCoordinate.getCoordinate();
        FieldState opponentFieldState = boardHandler.getOpponentFieldStatus(player, shotCoordinate);

        if (opponentFieldState.equals(FieldState.NOT_HIT_MAST)) {
            boardHandler.markHit(player, shotCoordinate);
        }

        if (opponentFieldState.equals(FieldState.EMPTY)) {
            boardHandler.markMiss(player, shotCoordinate);
        }

        logger.logInfo(BoardHandlerQueueListener.class, boardHandler.toString());

        queueService.sendObjectToQueue(Queues._17ShotHandlerFieldStatusQueue,
                new FieldStatus(
                        shotCoordinate,
                        opponentFieldState,
                        player
                )
        );

        queueService.sendObjectToQueue(Queues._18PlayingBoardsViewQueue,
                new BoardsView(player,
                        new PlayerBoardView(boardHandler.getPlayerBoard(player)),
                        new OpponentBoardView(boardHandler.getPlayerShotsMap(player)))
                );
    }

    @RabbitListener(queues = "BoardHandlerPlayerQueue") // 6
    void listenOnBoardHandlerPlayerQueue(PlayerConfiguration playerConfiguration) {
        logger.logInfo(BoardHandlerQueueListener.class, playerConfiguration.toString());
        Player player = playerConfiguration.getPlayer();
        boardHandler.addPlayer(player);
        boardHandler.addPlayerToRoom(new Room("1"), player);
    }

    @RabbitListener(queues = "BoardHandlerFleetPlacementQueue") // 7
    void listenOnBoardHandlerFleetPlacementQueue(FleetPlacement fleetPlacement) {
        logger.logInfo(BoardHandlerQueueListener.class, fleetPlacement.toString());
        Player player = fleetPlacement.getPlayer();
        Fleet fleet = fleetPlacement.getFleet();
        boardHandler.addFleetForPlayer(player, fleet);
    }
}
