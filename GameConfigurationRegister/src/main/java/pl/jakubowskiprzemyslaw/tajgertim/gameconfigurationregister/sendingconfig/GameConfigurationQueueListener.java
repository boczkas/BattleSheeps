package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@Service
class GameConfigurationQueueListener {

    private final LoggerService loggerService;
    private final ApplicationEventPublisher publisher;

    GameConfigurationQueueListener(LoggerService loggerService, ApplicationEventPublisher publisher) {
        this.loggerService = loggerService;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "GameConfigurationRegistrationQueue")
    void getGameConfigurationFromGameConfigurationQueue(GameConfiguration gameConfiguration) {
        loggerService.logInfo(GameConfigurationQueueListener.class, gameConfiguration.toString());
        GameConfiguredEvent event = new GameConfiguredEvent(this, gameConfiguration);
        publisher.publishEvent(event);
    }
}
