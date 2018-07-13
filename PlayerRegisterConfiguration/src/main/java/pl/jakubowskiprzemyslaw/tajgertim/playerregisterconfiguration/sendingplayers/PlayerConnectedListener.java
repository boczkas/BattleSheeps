package pl.jakubowskiprzemyslaw.tajgertim.playerregisterconfiguration.sendingplayers;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.PlayerConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
class PlayerConnectedListener implements ApplicationListener<PlayerConnectedEvent> {

    private final QueueService queueService;
    private int counter = 0;

    PlayerConnectedListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(PlayerConnectedEvent event) {
        counter++;
        if (counter == 2) {
            Confirmation playersRegistered = new PlayerConfigurationConfirmation();
            queueService.sendObjectToQueue(Queues._5GameReadyValidationQueue, playersRegistered);
            counter = 0;
        }
    }
}
