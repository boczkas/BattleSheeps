package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.NoSuchPlayerException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.OpponentBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.PlayerBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class PlayerShootCoordinateEventListener implements ApplicationListener<PlayerShootCoordinateEvent> {

    private final QueueService queueService;
    private final BoardHandler boardHandler;

    @Autowired
    public PlayerShootCoordinateEventListener(QueueService queueService, BoardHandler boardHandler) {
        this.queueService = queueService;
        this.boardHandler = boardHandler;
    }

    @Override
    public void onApplicationEvent(PlayerShootCoordinateEvent event) {

        PlayerShootCoordinate playerShootCoordinate = event.getCoordinate();

        Player player = playerShootCoordinate.getPlayer();
        Coordinate shotCoordinate = playerShootCoordinate.getCoordinate();
        FieldState opponentFieldState = null;

        try { //creation of this ugly construction was caused by the fact that getOpponentFieldStatus and markHit methods can throw an exception
            opponentFieldState = boardHandler.getOpponentFieldStatus(player, shotCoordinate);

            switch (opponentFieldState) {
                case NOT_HIT_MAST:
                    boardHandler.markHit(player, shotCoordinate);
                    break;
                case EMPTY:
                    boardHandler.markMiss(player, shotCoordinate);
                    break;
            }

        } catch (NoSuchPlayerException | NoMastAtPositionException e) {
            //TODO: do sth with this, logger or something
        }

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
