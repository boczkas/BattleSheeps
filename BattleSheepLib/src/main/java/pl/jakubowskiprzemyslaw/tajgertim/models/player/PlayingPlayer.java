package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

import java.util.Objects;

public class PlayingPlayer implements QueueObject {

    private static final long serialVersionUID = -3479665015132509447L;
    private final Player player;

    public PlayingPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingPlayer that = (PlayingPlayer) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {

        return Objects.hash(player);
    }

    @Override
    public String toString() {
        return "PlayingPlayer{" +
                "player=" + player +
                '}';
    }
}
