package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerShootResultEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class JudgeQueueListenerTest {

    private Player player;
    private ApplicationEventPublisher publisher;
    private JudgeQueueListener judgeQueueListener;
    private ShootResult shootResult;

    @BeforeMethod
    public void setUp() {
        player = new Player("", "");
        shootResult = ShootResult.UNKNOWN;
        publisher = mock(ApplicationEventPublisher.class);
        LoggerService loggerService = mock(LoggerService.class);

        judgeQueueListener = new JudgeQueueListener(loggerService, publisher);
    }

    public void listenOnJudgePlayerShootResultQueue_getPlayerShootResult_triggerEvent() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player, shootResult);
        judgeQueueListener.listenOnJudgePlayerShootResultQueue(playerShootResult);

        PlayerShootResultEvent event = new PlayerShootResultEvent(this, playerShootResult);

        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnJudgePlayerShootResultQueue_getObject_throwClassCastException() {
        Object object = new Object();
        judgeQueueListener.listenOnJudgePlayerShootResultQueue((PlayerShootResult) object);
    }
}
