package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class ShotActionEventListener implements ApplicationListener<ShotActionEvent> {

    private final QueueService queueService;

    @Autowired
    public ShotActionEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(ShotActionEvent event) {
        PlayerAction shotAction = event.getAction();
        Shot shot = (Shot) shotAction.getAction();
        Player player = shotAction.getPlayer();
        Coordinate coordinate = shot.getCoordinate();

        queueService.sendObjectToQueue(Queues._12BoardHandlerShotQueryQueue, new PlayerShootCoordinate(player, coordinate));
    }
}
