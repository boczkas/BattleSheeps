package pl.jakubowskiprzemyslaw.tajgertim;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.FieldStatusEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.FieldStatusEventListener;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class ShotHandlerFieldStatusEventListenerTest {

    private FieldStatusEventListener eventListener;
    private QueueService queueService;
    private FieldStatus fieldStatus;
    private Player player;
    private Coordinate coordinate;

    private static final int COORDINATE_X = 1;
    private static final int COORDINATE_Y = 1;

    @BeforeMethod
    public void setUp() {
        queueService = mock(QueueService.class);
        player = new Player("", "");
        coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        eventListener = new FieldStatusEventListener(queueService);
    }

    public void sendingFieldStatusNotHitMast_SendsShotResultHit_ToQueue15() {
        fieldStatus = new FieldStatus(coordinate, FieldState.NOT_HIT_MAST, player);
        FieldStatusEvent event = new FieldStatusEvent(this, fieldStatus);
        PlayerShootResult result = new PlayerShootResult(player, ShootResult.HIT);

        eventListener.onApplicationEvent(event);

        verify(queueService).sendObjectToQueue(Queues._15JudgePlayerShootResultQueue, result);
    }

    public void sendingFieldStatusHitMast_SendsShotResultMiss_ToQueue15() {
        fieldStatus = new FieldStatus(coordinate, FieldState.HIT_MAST, player);
        FieldStatusEvent event = new FieldStatusEvent(this, fieldStatus);
        PlayerShootResult result = new PlayerShootResult(player, ShootResult.MISS);

        eventListener.onApplicationEvent(event);

        verify(queueService).sendObjectToQueue(Queues._15JudgePlayerShootResultQueue, result);
    }


    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object event = new Object();
        eventListener.onApplicationEvent((FieldStatusEvent) event);
    }
}
