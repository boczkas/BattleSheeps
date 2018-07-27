package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.QueueOpponents;
import pl.jakubowskiprzemyslaw.tajgertim.models.round.NextRoundStatus;

public class NextRoundStatusEvent extends ApplicationEvent {

    private static final long serialVersionUID = 2624207274552619041L;
    private NextRoundStatus nextRoundStatus;
    private Player currentPlayer;
    private QueueOpponents queueOpponents;

    public NextRoundStatusEvent(Object source, NextRoundStatus nextRoundStatus, Player currentPlayer, QueueOpponents queueOpponents) {
        super(source);
        this.nextRoundStatus = nextRoundStatus;
        this.currentPlayer = currentPlayer;
        this.queueOpponents = queueOpponents;
    }

    public NextRoundStatus getNextRoundStatus() {
        return nextRoundStatus;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public QueueOpponents getQueueOpponents() {
        return queueOpponents;
    }
}
