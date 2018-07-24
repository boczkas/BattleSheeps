package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.RoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@BattleShipQueueInteractionHandler
public class JudgeQueueListener {

    private final QueueService queueService;
    private final LoggerService logger;

    public JudgeQueueListener(QueueService queueService, LoggerService logger) {
        this.queueService = queueService;
        this.logger = logger;
    }

    @RabbitListener(queues = "JudgePlayerShootResultQueue")
    public void listenOnJudgePlayerShootResultQueue(PlayerShootResult playerShootResult) {
        logger.logInfo(JudgeQueueListener.class, "Received message" + playerShootResult);

        ShootResult shootResult = playerShootResult.getShootResult();

        RoundStatus roundStatus;
        switch (shootResult) {
            case HIT:
                roundStatus = RoundStatus.SAME_PLAYER;
                break;
            case MISS:
                roundStatus = RoundStatus.NEXT_PLAYER;
                break;
            default:
                roundStatus = RoundStatus.GAME_END;
                break;
        }

        queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus(roundStatus));
    }
}
