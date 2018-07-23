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
    public void listenOnJudgePlayerShootResultQueue (PlayerShootResult playerShootResult) {
        logger.logInfo(JudgeQueueListener.class, "Received message" + playerShootResult);

        ShootResult shootResult = playerShootResult.getShootResult();

        RoundStatus roundStatus = RoundStatus.GAME_END;

        if (shootResult.equals(ShootResult.HIT)) {
            roundStatus = RoundStatus.SAME_PLAYER;
        }
        else if (shootResult.equals(ShootResult.MISS)) {
            roundStatus = RoundStatus.NEXT_PLAYER;
        }

        queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus(roundStatus));
    }
}
