package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.PlayingPlayer;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.QueueOpponents;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.RoundStatus;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class NextRoundEventListener implements ApplicationListener<NextRoundStatusEvent> {

    private final QueueService queueService;

    @Autowired
    public NextRoundEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(NextRoundStatusEvent event) {
        RoundStatus roundStatus = event.getNextRoundStatus().getRoundStatus();
        Player currentPlayer = event.getCurrentPlayer();
        Player nextPlayer = currentPlayer;
        QueueOpponents queueOpponents = event.getQueueOpponents();

        if (roundStatus.equals(RoundStatus.NEXT_PLAYER)) {
            nextPlayer = queueOpponents.getOpponent(currentPlayer);
        }

        queueService.sendObjectToQueue(Queues._19PlayingPlayerQueue, new PlayingPlayer(nextPlayer));
    }
}
