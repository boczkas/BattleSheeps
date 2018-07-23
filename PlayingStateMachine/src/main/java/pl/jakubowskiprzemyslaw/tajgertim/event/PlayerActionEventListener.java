package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Move;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class PlayerActionEventListener implements ApplicationListener<PlayerActionEvent> {

    private final QueueService queueService;

    @Autowired
    public PlayerActionEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(PlayerActionEvent event) {
        PlayerAction playerAction = event.getPlayerAction();

        if(playerAction.getAction() instanceof Shot) {
            queueService.sendObjectToQueue(Queues._10ShotHandlerPlayerShotQueue, playerAction);
        }

        if(playerAction.getAction() instanceof Move) {
            queueService.sendObjectToQueue(Queues._11MoveHandlerPlayerMoveQueue, playerAction);
        }

    }
}
