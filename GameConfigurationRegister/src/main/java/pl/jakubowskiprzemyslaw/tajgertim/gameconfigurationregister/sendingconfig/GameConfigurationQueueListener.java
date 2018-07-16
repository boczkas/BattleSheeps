package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
class GameConfigurationQueueListener {

    private final QueueService queueService;
    private final LoggerService loggerService;
    private final ApplicationEventPublisher publisher;

    @Autowired
    GameConfigurationQueueListener(QueueService queueService, LoggerService loggerService, ApplicationEventPublisher publisher) {
        this.queueService = queueService;
        this.loggerService = loggerService;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "GameConfigurationRegistrationQueue")
    void getGameConfigurationFromGameConfigurationQueue(GameConfiguration gameConfiguration) {
        loggerService.logInfo(GameConfigurationQueueListener.class, gameConfiguration.toString());
        GameRegisteredEvent event = new GameRegisteredEvent(this);
        publisher.publishEvent(event);
        queueService.sendObjectToQueue(Queues._3FleetPlacementSizeQueue, gameConfiguration);
    }
}
