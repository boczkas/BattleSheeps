package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.RoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
public class JudgeService {

    private final QueueService queueService;

    public JudgeService(QueueService queueService) {
        this.queueService = queueService;
    }

    @RabbitListener(queues = "JudgePlayerShootResultQueueTest")
    public void listenOnJudgePlayerShootResultQueue (PlayerShootResult playerShootResult) {
        System.out.println("Received message" + playerShootResult);

        ShootResult shootResult = playerShootResult.getShootResult();

        if(shootResult.equals(ShootResult.HIT)){
            queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus(RoundStatus.SAME_PLAYER));
        }

        if(shootResult.equals(ShootResult.MISS)){
            queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus(RoundStatus.NEXT_PLAYER));
        }
    }
}
