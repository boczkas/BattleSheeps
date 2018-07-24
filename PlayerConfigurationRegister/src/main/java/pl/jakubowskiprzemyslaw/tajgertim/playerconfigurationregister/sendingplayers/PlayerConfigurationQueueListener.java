package pl.jakubowskiprzemyslaw.tajgertim.playerconfigurationregister.sendingplayers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@Service
class PlayerConfigurationQueueListener {

    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    @Autowired
    PlayerConfigurationQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "PlayerRegistrationQueue")
    void getPlayerFromPlayerRegistrationQueue(PlayerConfiguration playerConfiguration) {
        logger.logInfo(PlayerConfigurationQueueListener.class, playerConfiguration.toString());
        PlayerConnectedEvent event = new PlayerConnectedEvent(this, playerConfiguration);
        publisher.publishEvent(event);
    }
}
