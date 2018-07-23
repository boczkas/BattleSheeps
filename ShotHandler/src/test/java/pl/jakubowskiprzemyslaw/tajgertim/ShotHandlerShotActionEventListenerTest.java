package pl.jakubowskiprzemyslaw.tajgertim;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.ShotActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.ShotActionEventListener;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class ShotHandlerShotActionEventListenerTest {

    private ShotActionEventListener eventListener;
    private QueueService queueService;
    private Player player;
    private Coordinate coordinate;
    private PlayerAction action;

    private static final int COORDINATE_X = 1;
    private static final int COORDINATE_Y = 1;

    @BeforeMethod
    public void setUp() {
        queueService = mock(QueueService.class);
        player = new Player("", "");
        coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        eventListener = new ShotActionEventListener(queueService);
        action = new PlayerAction(player, new Shot(coordinate));
    }

    public void sendingShotAction_SendsPlayerShootCoordinate_ToQueue12() {
        ShotActionEvent event = new ShotActionEvent(this, action);
        PlayerShootCoordinate playerShootCoordinate = new PlayerShootCoordinate(player, coordinate);
        eventListener.onApplicationEvent(event);

        verify(queueService).sendObjectToQueue(Queues._12BoardHandlerShotQueryQueue, playerShootCoordinate);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object event = new Object();
        eventListener.onApplicationEvent((ShotActionEvent) event);
    }


}
