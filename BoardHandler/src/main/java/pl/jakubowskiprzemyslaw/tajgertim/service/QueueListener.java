package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
public class QueueListener {

    private final QueueService queueService;

    public QueueListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @RabbitListener(queues = "BoardHandlerShotQueryQueueTest")
    public void listenOnBoardHandlerShotQueryQueueTest(PlayerShootCoordinate playerShootCoordinate) {
        System.out.println("Received message" + playerShootCoordinate);



        queueService.sendObjectToQueue(Queues._17ShotHandlerFieldStatusQueue,
                new FieldStatus(
                        playerShootCoordinate.getCoordinate(),
                        FieldState.EMPTY,
                        playerShootCoordinate.getPlayer()
                )
        );
    }
}
