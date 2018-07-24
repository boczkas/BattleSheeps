package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class PlayingStateMachineQueueListener {

    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    public PlayingStateMachineQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "PlayingStateMachinePlayerActionQueue")
    public void listenOnPlayingStateMachinePlayerActionQueue(PlayerAction playerAction) {
        logger.logInfo(PlayingStateMachineQueueListener.class, "Received message" + playerAction);
        PlayerActionEvent event = new PlayerActionEvent(this, playerAction);
        publisher.publishEvent(event);
    }

    @RabbitListener(queues = "PlayingStateMachineNextRoundStatusQueue")
    public void listenOnPlayingStateMachineNextRoundStatusQueue(NextRoundStatus nextRoundStatus) {
        logger.logInfo(PlayingStateMachineQueueListener.class, nextRoundStatus.toString());
    }
}
