package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.NoSuchPlayerException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoShipAtCoordinateException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.OpponentBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.PlayerBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
public class QueueListener {

    private final QueueService queueService;
    private final BoardHandler boardHandler;

    public QueueListener(QueueService queueService, BoardHandler boardHandler) {
        this.queueService = queueService;
        this.boardHandler = boardHandler;
    }

    @RabbitListener(queues = "BoardHandlerShotQueryQueueTest")  //12
    public void listenOnBoardHandlerShotQueryQueueTest(PlayerShootCoordinate playerShootCoordinate) throws NoSuchPlayerException, NoMastAtPositionException, NoShipAtCoordinateException {
        System.out.println("Received message" + playerShootCoordinate);

        Player player = playerShootCoordinate.getPlayer();
        Coordinate shotCoordinate = playerShootCoordinate.getCoordinate();
        FieldState opponentFieldState = boardHandler.getOpponentFieldStatus(player, shotCoordinate);

        if (opponentFieldState.equals(FieldState.NOT_HIT_MAST)) {
            boardHandler.markHit(player, shotCoordinate);
        }

        if (opponentFieldState.equals(FieldState.EMPTY)) {
            boardHandler.markMiss(player, shotCoordinate);
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println(boardHandler);
        System.out.println("----------------------------------------------------------------");

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
}
