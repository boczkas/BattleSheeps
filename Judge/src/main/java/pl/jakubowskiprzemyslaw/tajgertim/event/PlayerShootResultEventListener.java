package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.RoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class PlayerShootResultEventListener implements ApplicationListener<PlayerShootResultEvent> {

    private final QueueService queueService;

    @Autowired
    public PlayerShootResultEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(PlayerShootResultEvent event) {
        PlayerShootResult playerShootResult = event.getPlayerShootResult();
        ShootResult shootResult = playerShootResult.getShootResult();

        RoundStatus roundStatus;
        switch (shootResult) {
            case HIT:
                roundStatus = RoundStatus.SAME_PLAYER;
                break;
            case MISS:
                roundStatus = RoundStatus.NEXT_PLAYER;
                break;
            default:
                roundStatus = RoundStatus.GAME_END;
                break;
        }

        queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus(roundStatus));
    }
}
