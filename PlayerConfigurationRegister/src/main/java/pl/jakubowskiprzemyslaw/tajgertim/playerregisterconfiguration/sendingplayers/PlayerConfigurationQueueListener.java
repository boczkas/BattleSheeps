package pl.jakubowskiprzemyslaw.tajgertim.playerregisterconfiguration.sendingplayers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
class PlayerConfigurationQueueListener {

    private final QueueService queueService;
    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    @Autowired
    PlayerConfigurationQueueListener(QueueService queueService, LoggerService logger, ApplicationEventPublisher publisher) {
        this.queueService = queueService;
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "PlayerRegistrationQueue")
    void getPlayerFromPlayerRegistrationQueue(PlayerConfiguration player) {
        PlayerConnectedEvent event = new PlayerConnectedEvent(this);
        publisher.publishEvent(event);
        queueService.sendObjectToQueue(Queues._6BoardHandlerPlayerQueue, player);
        logger.logInfo(PlayerConfigurationQueueListener.class, player.toString());
    }
}
