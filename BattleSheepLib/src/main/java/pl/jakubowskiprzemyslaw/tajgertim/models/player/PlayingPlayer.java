package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

public class PlayingPlayer implements QueueObject {

    private static final long serialVersionUID = 8288165593921322085L;
    private final Player player;

    public PlayingPlayer(Player player) {
        this.player = player;
    }

    public String getPlayerName() {
        return player.getName();
    }
}
