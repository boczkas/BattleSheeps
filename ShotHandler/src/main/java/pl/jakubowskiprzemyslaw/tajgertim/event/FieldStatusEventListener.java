package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class FieldStatusEventListener implements ApplicationListener<FieldStatusEvent> {

    private final QueueService queueService;

    @Autowired
    public FieldStatusEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(FieldStatusEvent event) {
        FieldStatus fieldStatus = event.getFieldStatus();
        FieldState fieldState = fieldStatus.getFieldState();

        Player player = fieldStatus.getPlayer();
        QueueObject playerShootResult;

        switch (fieldState) {
            case NOT_HIT_MAST:
                playerShootResult = new PlayerShootResult(player, ShootResult.HIT);
                break;
            default: //in if has been else, so I think default is good enough.
                playerShootResult = new PlayerShootResult(player, ShootResult.MISS);
                break;
        }

        queueService.sendObjectToQueue(Queues._15JudgePlayerShootResultQueue, playerShootResult);
    }
}
