package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.GameConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class GameConfiguredListener implements ApplicationListener<GameRegisteredEvent> {

    private final QueueService queueService;
    private final LoggerService loggerService;

    @Autowired
    public GameConfiguredListener(QueueService queueService, LoggerService loggerService) {
        this.queueService = queueService;
        this.loggerService = loggerService;
    }

    @Override
    public void onApplicationEvent(GameRegisteredEvent event) {
        Confirmation gameConfigured = new GameConfigurationConfirmation();
        queueService.sendObjectToQueue(Queues._5GameReadyValidationQueue, gameConfigured);
        loggerService.logInfo(GameConfiguredListener.class, "Eloszka");
    }
}
