package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Move;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@BattleShipQueueInteractionHandler
public class PlayingStateMachineQueueListener {

    private final QueueService queueService;
    private final LoggerService logger;

    public PlayingStateMachineQueueListener(QueueService queueService, LoggerService logger) {
        this.queueService = queueService;
        this.logger = logger;
    }

    @RabbitListener(queues = "PlayingStateMachinePlayerActionQueue")
    public void listenOnPlayingStateMachinePlayerActionQueue(PlayerAction playerAction) {

        logger.logInfo(PlayingStateMachineQueueListener.class, "Received message" + playerAction);

        if(playerAction.getAction() instanceof Shot) {
            queueService.sendObjectToQueue(Queues._10ShotHandlerPlayerShotQueue, playerAction);
        }

        if(playerAction.getAction() instanceof Move) {
            queueService.sendObjectToQueue(Queues._11MoveHandlerPlayerMoveQueue, playerAction);
        }
    }

    @RabbitListener(queues = "PlayingStateMachineNextRoundStatusQueue")
    public void listenOnPlayingStateMachineNextRoundStatusQueue(NextRoundStatus nextRoundStatus) {
        logger.logInfo(PlayingStateMachineQueueListener.class, nextRoundStatus.toString());
    }
}
