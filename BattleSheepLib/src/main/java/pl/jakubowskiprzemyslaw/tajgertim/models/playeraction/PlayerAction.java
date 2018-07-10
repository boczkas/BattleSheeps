package pl.jakubowskiprzemyslaw.tajgertim.models.playeraction;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Action;

public class PlayerAction implements QueueObject {
    private static final long serialVersionUID = 7276281482073190313L;
    private final Player player;
    private final Action action;

    public PlayerAction(Player player, Action action) {
        this.player = player;
        this.action = action;
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "player=" + player +
                ", action=" + action +
                '}';
    }
}
