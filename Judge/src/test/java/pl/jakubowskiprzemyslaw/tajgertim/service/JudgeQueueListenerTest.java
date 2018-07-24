package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.RoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class JudgeQueueListenerTest {

    private Player player;
    private QueueService queueService;
    private JudgeQueueListener judgeQueueListener;

    @BeforeMethod
    public void setUp() {
        player = new Player("", "");
        queueService = mock(QueueService.class);
        LoggerService loggerService = mock(LoggerService.class);

        judgeQueueListener = new JudgeQueueListener(queueService, loggerService);
    }

    public void shootResult_HIT_sendToQueueRoundStatus_SAME_PLAYER() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player,ShootResult.HIT);

        judgeQueueListener.listenOnJudgePlayerShootResultQueue(playerShootResult);

        NextRoundStatus nextRoundStatus = new NextRoundStatus(RoundStatus.SAME_PLAYER);
        verify(queueService).sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, nextRoundStatus);
    }

    public void shootResult_MISS_sendToQueueRoundStatus_NEXT_PLAYER() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player,ShootResult.MISS);

        judgeQueueListener.listenOnJudgePlayerShootResultQueue(playerShootResult);

        NextRoundStatus nextRoundStatus = new NextRoundStatus(RoundStatus.NEXT_PLAYER);
        verify(queueService).sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, nextRoundStatus);
    }

    public void shootResult_UNKNOWN_sendToQueueRoundStatus_GAME_END() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player,ShootResult.UNKNOWN);

        judgeQueueListener.listenOnJudgePlayerShootResultQueue(playerShootResult);

        NextRoundStatus nextRoundStatus = new NextRoundStatus(RoundStatus.GAME_END);
        verify(queueService).sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, nextRoundStatus);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void object_throwClassCastException() {
        Object object = new Object();
        judgeQueueListener.listenOnJudgePlayerShootResultQueue((PlayerShootResult) object);
    }

}
