package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
class GameReadyValidatorQueueListener {

    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    GameReadyValidatorQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "GameReadyValidationQueue")
    void getConfirmationsFromGameReadyValidatorQueue(Confirmation confirmation) {
        ConfirmationReceivedEvent event = new ConfirmationReceivedEvent(this);
        publisher.publishEvent(event);
        logger.logInfo(GameReadyValidatorQueueListener.class, confirmation.toString());
    }
}
