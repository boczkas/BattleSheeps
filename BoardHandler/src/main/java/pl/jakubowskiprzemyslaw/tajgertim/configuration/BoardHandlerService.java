package pl.jakubowskiprzemyslaw.tajgertim.configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
public class BoardHandlerService {

    private final QueueService queueService;

    public BoardHandlerService(QueueService queueService) {
        this.queueService = queueService;
    }

    @RabbitListener(queues = "BoardHandlerShotQueryQueueTest")
    public void listenOnJudgePlayerShootResultQueue(PlayerShootCoordinate playerShootCoordinate) {
        System.out.println("Received message" + playerShootCoordinate);
        queueService.sendObjectToQueue("ShotHandlerFieldStatusQueueTest",
                new FieldStatus(
                        playerShootCoordinate.getCoordinate(),
                        FieldState.EMPTY,
                        playerShootCoordinate.getPlayer()
                )
        );
    }
}
