package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
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
        GameConfiguration gameConfiguration = event.getGameConfiguration();
        queueService.sendObjectToQueue(Queues._3FleetPlacementSizeQueue, gameConfiguration);
        Confirmation gameConfigured = new GameConfigurationConfirmation();
        queueService.sendObjectToQueue(Queues._5GameReadyValidationQueue, gameConfigured);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConfiguredEventListener that = (GameConfiguredEventListener) o;
        return Objects.equals(queueService, that.queueService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueService);
    }
}
