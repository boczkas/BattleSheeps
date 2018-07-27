package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

public class PlayingPlayer implements QueueObject {

    private static final long serialVersionUID = -3479665015132509447L;
    private final Player player;

    public PlayingPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "PlayingPlayer{" +
                "player=" + player +
                '}';
    }
}
