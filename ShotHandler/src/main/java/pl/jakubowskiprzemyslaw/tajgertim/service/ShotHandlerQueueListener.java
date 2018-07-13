package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Service
public class ShotHandlerQueueListener {
    private final QueueService queueService;
    private final LoggerService logger;

    public ShotHandlerQueueListener(QueueService queueService, LoggerService logger) {
        this.queueService = queueService;
        this.logger = logger;
    }

    @RabbitListener(queues = "ShotHandlerPlayerShotQueue")  // 10
    public void listenOnShotHandlerPlayerShotQueue(PlayerAction shotAction) {
        logger.logInfo(ShotHandlerQueueListener.class, "Received message " + shotAction);
        Shot shot = (Shot) shotAction.getAction();

        Player player = shotAction.getPlayer();
        Coordinate coordinate = shot.getCoordinate();
        queueService.sendObjectToQueue(Queues._12BoardHandlerShotQueryQueue, new PlayerShootCoordinate(player, coordinate));
    }

    @RabbitListener(queues = "ShotHandlerFieldStatusQueue") // 17
    public void listenOnShotHandlerFieldStatusQueue(FieldStatus fieldStatus) {
        logger.logInfo(ShotHandlerQueueListener.class, "Received message " + fieldStatus);
        FieldState fieldState = fieldStatus.getFieldState();

        Player player = fieldStatus.getPlayer();
        if (fieldState.equals(FieldState.NOT_HIT_MAST)) {
            queueService.sendObjectToQueue(Queues._15JudgePlayerShootResultQueue, new PlayerShootResult(player, ShootResult.HIT));
        } else {
            queueService.sendObjectToQueue(Queues._15JudgePlayerShootResultQueue, new PlayerShootResult(player, ShootResult.MISS));
        }
    }
}
