package pl.jakubowskiprzemyslaw.tajgertim;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.FieldStatusEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.ShotActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.service.ShotHandlerQueueListener;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class ShotHandlerQueueListenerTest {

    private ApplicationEventPublisher publisher;
    private ShotHandlerQueueListener queueListener;

    @BeforeMethod
    public void setUp() {
        LoggerService loggerService = mock(LoggerService.class);
        publisher = mock(ApplicationEventPublisher.class);
        queueListener = new ShotHandlerQueueListener(loggerService, publisher);
    }

    public void listeningOnShotHandlerPlayerShotQueue_getShotAction_triggersEvent() {
        PlayerAction action = mock(PlayerAction.class);
        queueListener.listenOnShotHandlerPlayerShotQueue(action);
        ShotActionEvent event = new ShotActionEvent(this, action);

        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnShotHandlerPlayerShotQueue_getObject_throwClassCastException() {
        Object object = new Object();
        queueListener.listenOnShotHandlerPlayerShotQueue((PlayerAction) object);
    }

    public void listeningOnShotHandlerFieldStatusQueue_getShotAction_triggersEvent() {
        FieldStatus status = mock(FieldStatus.class);
        queueListener.listenOnShotHandlerFieldStatusQueue(status);
        FieldStatusEvent event = new FieldStatusEvent(this, status);

        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnShotHandlerFieldStatusQueue_getObject_throwClassCastException() {
        Object object = new Object();
        queueListener.listenOnShotHandlerFieldStatusQueue((FieldStatus) object);
    }
}
