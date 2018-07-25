package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.event.FieldStatusEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.ShotActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class ShotHandlerQueueListener {
    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    public ShotHandlerQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "ShotHandlerPlayerShotQueue")  // 10
    public void listenOnShotHandlerPlayerShotQueue(PlayerAction shotAction) {
        logger.logInfo(ShotHandlerQueueListener.class, "Received message " + shotAction);
        ShotActionEvent event = new ShotActionEvent(this, shotAction);
        publisher.publishEvent(event);
    }

    @RabbitListener(queues = "ShotHandlerFieldStatusQueue") // 17
    public void listenOnShotHandlerFieldStatusQueue(FieldStatus fieldStatus) {
        logger.logInfo(ShotHandlerQueueListener.class, "Received message " + fieldStatus);
        FieldStatusEvent event = new FieldStatusEvent(this, fieldStatus);
        publisher.publishEvent(event);
    }
}
