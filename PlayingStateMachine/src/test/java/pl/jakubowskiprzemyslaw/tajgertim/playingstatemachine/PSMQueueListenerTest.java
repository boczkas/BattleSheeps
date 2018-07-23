package pl.jakubowskiprzemyslaw.tajgertim.playingstatemachine;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.service.PlayingStateMachineQueueListener;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class PSMQueueListenerTest {

    private ApplicationEventPublisher publisher;
    private PlayingStateMachineQueueListener queueListener;

    @BeforeMethod
    public void setUp() {
        LoggerService loggerService = mock(LoggerService.class);
        publisher = mock(ApplicationEventPublisher.class);
        queueListener = new PlayingStateMachineQueueListener(loggerService, publisher);
    }

    public void listenOnPlayerActionQueue_getPlayerAction_triggersEvent () {
        PlayerAction action = mock(PlayerAction.class);
        queueListener.listenOnPlayingStateMachinePlayerActionQueue(action);
        PlayerActionEvent event = new PlayerActionEvent(this, action);
        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnPlayerActionQueue_getObject_throwClassCastException() {
        Object object = new Object();
        queueListener.listenOnPlayingStateMachinePlayerActionQueue((PlayerAction) object);
    }
}
