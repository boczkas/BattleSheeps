package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Move;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
public class PlayingStateMachineService {

    private final QueueService queueService;

    public PlayingStateMachineService(QueueService queueService) {
        this.queueService = queueService;
    }

    @RabbitListener(queues = "PlayingStateMachinePlayerActionQueueTest")
    public void listenOnPlayingStateMachinePlayerActionQueue(PlayerAction playerAction) {
        System.out.println("Received message" + playerAction);

        if(playerAction.getAction() instanceof Shot) {
            queueService.sendObjectToQueue("ShotHandlerPlayerShotQueueTest", playerAction);
        }

        if(playerAction.getAction() instanceof Move) {
            queueService.sendObjectToQueue("MoveHandlerPlayerMoveQueueTest", playerAction);
        }
    }

    @RabbitListener(queues = "PlayingStateMachineNextRoundStatusQueueTest")
    public void listenOnPlayingStateMachineNextRoundStatusQueueTest(NextRoundStatus nextRoundStatus) {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(nextRoundStatus);
        System.out.println("------------------------------------------------------------------------------------");
    }
}