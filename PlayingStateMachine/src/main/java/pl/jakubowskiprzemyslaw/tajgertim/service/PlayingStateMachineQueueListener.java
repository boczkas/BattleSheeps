package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.event.NextRoundStatusEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.QueueOpponents;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class PlayingStateMachineQueueListener {

    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;
    private QueueOpponents queueOpponents;
    private Player currentPlayer;

    public PlayingStateMachineQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "PlayingStateMachineOpponentsQueue")
    public void listenOnPlayingStateMachineOpponentsQueue(QueueOpponents queueOpponents){
        logger.logInfo(PlayingStateMachineQueueListener.class, "Received message" + queueOpponents);
        this.queueOpponents = queueOpponents;
    }

    @RabbitListener(queues = "PlayingStateMachinePlayerActionQueue")
    public void listenOnPlayingStateMachinePlayerActionQueue(PlayerAction playerAction) {
        logger.logInfo(PlayingStateMachineQueueListener.class, "Received message" + playerAction);
        PlayerActionEvent event = new PlayerActionEvent(this, playerAction);
        publisher.publishEvent(event);
        currentPlayer = playerAction.getPlayer();
    }

    @RabbitListener(queues = "PlayingStateMachineNextRoundStatusQueue")
    public void listenOnPlayingStateMachineNextRoundStatusQueue(NextRoundStatus nextRoundStatus) {
        logger.logInfo(PlayingStateMachineQueueListener.class, nextRoundStatus.toString());

        NextRoundStatusEvent nextRoundStatusEvent = new NextRoundStatusEvent(this, nextRoundStatus, currentPlayer, queueOpponents);
        publisher.publishEvent(nextRoundStatusEvent);
    }

}
