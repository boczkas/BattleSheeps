package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.NoSuchPlayerException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Test
public class PlayerShootCoordinateEventListenerTest {

    private PlayerShootCoordinateEventListener listener;
    private BoardHandler boardHandler;
    private QueueService queueService;

    private static final int COORDINATE_X = 1;
    private static final int COORDINATE_Y = 1;
    private Coordinate coordinate;
    private Player player;

    @BeforeMethod
    public void setUp() {
        boardHandler = mock(BoardHandler.class);
        queueService = mock(QueueService.class);
        listener = new PlayerShootCoordinateEventListener(queueService, boardHandler);
    }

    public void sendingPlayerShootCoordinateWithNotHitMast_SendsOpponentFieldState_ToQueue17() throws NoSuchPlayerException, NoMastAtPositionException {
        PlayerShootCoordinateEvent event = createEventForTest();
        FieldStatus fieldStatus = new FieldStatus(coordinate, FieldState.NOT_HIT_MAST, player, 10);
        when(boardHandler.getOpponent(player)).thenReturn(new Player("", ""));
        when(boardHandler.getOpponentFieldStatus(player, coordinate)).thenReturn(FieldState.NOT_HIT_MAST);
        when(boardHandler.getPlayerBoard(player)).thenReturn(new Board(new Fleet()));
        listener.onApplicationEvent(event);

        verify(queueService).sendObjectToQueue(Queues._17ShotHandlerFieldStatusQueue, fieldStatus);
        verify(boardHandler).markHit(player, coordinate);
    }

    public void sendingPlayerShootCoordinateWithHitMast_SendsOpponentFieldState_ToQueue17() throws NoSuchPlayerException {
        PlayerShootCoordinateEvent event = createEventForTest();
        FieldStatus fieldStatus = new FieldStatus(coordinate, FieldState.HIT_MAST, player, 10);
        when(boardHandler.getOpponentFieldStatus(player, coordinate)).thenReturn(FieldState.HIT_MAST);
        when(boardHandler.getOpponent(player)).thenReturn(new Player("", ""));
        when(boardHandler.getPlayerBoard(player)).thenReturn(new Board(new Fleet()));
        listener.onApplicationEvent(event);

        verify(queueService).sendObjectToQueue(Queues._17ShotHandlerFieldStatusQueue, fieldStatus);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object object = new Object();
        PlayerShootCoordinateEvent event = new PlayerShootCoordinateEvent(this, (PlayerShootCoordinate) object);
        listener.onApplicationEvent(event);
    }

    private PlayerShootCoordinateEvent createEventForTest() {
        player = new Player("", "");
        coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        PlayerShootCoordinate playerShootCoordinate = new PlayerShootCoordinate(player, coordinate);
        return new PlayerShootCoordinateEvent(this, playerShootCoordinate);
    }
}
