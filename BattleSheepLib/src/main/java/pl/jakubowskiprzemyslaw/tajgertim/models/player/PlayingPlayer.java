package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

public class PlayingPlayer implements QueueObject {

    private final Player player;

    public PlayingPlayer(Player player) {
        this.player = player;
    }
}
