package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.RoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class PlayerShootResultEventListenerTest {

    private QueueService queueService;
    private Player player;
    private PlayerShootResultEventListener playerShootResultEventListener;

    @BeforeMethod
    public void setUp() {
        player = new Player("", "");
        queueService = mock(QueueService.class);
        playerShootResultEventListener = new PlayerShootResultEventListener(queueService);
    }

    public void triggerEventWitsShootResult_HIT_send_SAME_PLAYER_ToQueue_14() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player, ShootResult.HIT, 10);
        PlayerShootResultEvent event = new PlayerShootResultEvent(this, playerShootResult);

        playerShootResultEventListener.onApplicationEvent(event);

        NextRoundStatus nextRoundStatus = new NextRoundStatus(RoundStatus.SAME_PLAYER);
        verify(queueService).sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, nextRoundStatus);
    }


    public void triggerEventWitsShootResult_MISS_send_NEXT_PLAYER_ToQueue_14() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player, ShootResult.MISS, 10);
        PlayerShootResultEvent event = new PlayerShootResultEvent(this, playerShootResult);

        playerShootResultEventListener.onApplicationEvent(event);

        NextRoundStatus nextRoundStatus = new NextRoundStatus(RoundStatus.NEXT_PLAYER);
        verify(queueService).sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, nextRoundStatus);
    }


    public void triggerEventWitsShootResult_UNKNOWN_send_GAME_END_ToQueue_14() {
        PlayerShootResult playerShootResult = new PlayerShootResult(player, ShootResult.UNKNOWN, 10);
        PlayerShootResultEvent event = new PlayerShootResultEvent(this, playerShootResult);

        playerShootResultEventListener.onApplicationEvent(event);

        NextRoundStatus nextRoundStatus = new NextRoundStatus(RoundStatus.GAME_END);
        verify(queueService).sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, nextRoundStatus);
    }


}
