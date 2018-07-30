package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QueueOpponents implements QueueObject {
    private static final long serialVersionUID = -1441279308004351636L;
    private List<Player> opponents;

    public QueueOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }

    public Player getOpponent(Player player) {
        return opponents.get((opponents.indexOf(player) + 1) % 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueOpponents that = (QueueOpponents) o;
        return Objects.equals(opponents, that.opponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opponents);
    }

    @Override
    public String toString() {
        return "QueueOpponents{" +
                "opponents=" + opponents +
                '}';
    }

    public Player getFirstPlayer() {
        return opponents.get(0);
    }
}
