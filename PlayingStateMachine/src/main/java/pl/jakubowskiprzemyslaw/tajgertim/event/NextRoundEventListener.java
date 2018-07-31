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
        Player nextPlayer;
        QueueOpponents queueOpponents = event.getQueueOpponents();
        PlayingPlayer playingPlayer = null;

        switch (roundStatus) {
            case FIRST_ROUND:
                nextPlayer = queueOpponents.getFirstPlayer();
                playingPlayer = new PlayingPlayer(nextPlayer, queueOpponents.getOpponent(nextPlayer).getName());
                break;
            case NEXT_PLAYER:
                nextPlayer = queueOpponents.getOpponent(currentPlayer);
                playingPlayer = new PlayingPlayer(nextPlayer, queueOpponents.getOpponent(nextPlayer).getName());
                break;
            case SAME_PLAYER:
                playingPlayer = new PlayingPlayer(currentPlayer, queueOpponents.getOpponent(currentPlayer).getName());
                break;
            case GAME_END:
                playingPlayer = new PlayingPlayer(currentPlayer, queueOpponents.getOpponent(currentPlayer).getName());
                playingPlayer.setWinner();
                break;
        }

        queueService.sendObjectToQueue(Queues._19PlayingPlayerQueue, playingPlayer);
    }
}
