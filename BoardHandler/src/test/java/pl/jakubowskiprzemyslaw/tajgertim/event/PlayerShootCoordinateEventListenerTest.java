package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;

@Test
public class PlayerShootCoordinateEventListenerTest {

    private PlayerShootCoordinateEventListener listener;
    private BoardHandler boardHandler;
    private QueueService queueService;

    @BeforeMethod
    public void setUp() {
        boardHandler = mock(BoardHandler.class);
        queueService = mock(QueueService.class);
        listener = new PlayerShootCoordinateEventListener(queueService, boardHandler);
    }

    public void sendingPlayerShootCoordinate_SendsFieldStatus_ToQueue17() {

    }

    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object object = new Object();
        PlayerShootCoordinateEvent event = new PlayerShootCoordinateEvent(this, (PlayerShootCoordinate) object);
        listener.onApplicationEvent(event);
    }
}
