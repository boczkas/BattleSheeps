package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.GameConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import java.util.Objects;

@Component
public class GameConfiguredEventListener implements ApplicationListener<GameConfiguredEvent> {

    private final QueueService queueService;

    @Autowired
    public GameConfiguredEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(GameConfiguredEvent event) {
        Confirmation gameConfigured = new GameConfigurationConfirmation();
        queueService.sendObjectToQueue(Queues._5GameReadyValidationQueue, gameConfigured);
    }

}
