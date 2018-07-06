package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
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
        queueService.sendObjectToQueue(Queues._14PlayingStateMachineNextRoundStatusQueue, new NextRoundStatus());
    }
}
