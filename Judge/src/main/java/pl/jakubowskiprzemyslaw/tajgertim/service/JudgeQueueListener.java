package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.event.FinalConfigurationConfirmationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerShootResultEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FinalConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class JudgeQueueListener {

    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public JudgeQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "JudgeStartQueue")
    public void listenOnJudgeStartQueue(FinalConfigurationConfirmation finalConfigurationConfirmation) {
        logger.logInfo(JudgeQueueListener.class, "Received message" + finalConfigurationConfirmation);
        FinalConfigurationConfirmationEvent event = new FinalConfigurationConfirmationEvent(this, finalConfigurationConfirmation);
        publisher.publishEvent(event);
    }

    @RabbitListener(queues = "JudgePlayerShootResultQueue")
    public void listenOnJudgePlayerShootResultQueue(PlayerShootResult playerShootResult) {
        logger.logInfo(JudgeQueueListener.class, "Received message" + playerShootResult);
        PlayerShootResultEvent event = new PlayerShootResultEvent(this, playerShootResult);
        publisher.publishEvent(event);
    }
}
