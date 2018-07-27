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
public class FinalConfigurationConfigurationEventListener implements ApplicationListener<FinalConfigurationConfirmationEvent> {

    private final QueueService queueService;

    @Autowired
    public FinalConfigurationConfigurationEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(FinalConfigurationConfirmationEvent event) {
        queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus(RoundStatus.NEXT_PLAYER));
    }
}
