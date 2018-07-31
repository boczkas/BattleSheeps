package pl.jakubowskiprzemyslaw.tajgertim.models.coordinates;


import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.Objects;

/**
 * Class used for communication between microservices BoardHandler
 * and ShotHandler to inform ShotHandler about Field status
 * after shot has been performed
 */

public class FieldStatus implements QueueObject {
    private static final long serialVersionUID = -8040984426644355070L;
    private Coordinate coordinate;
    private FieldState fieldState;
    private Player player;
    private int numberOfOtherMasts;

    public FieldStatus(Coordinate coordinate, FieldState fieldState, Player player, int masts) {
        this.coordinate = coordinate;
        this.fieldState = fieldState;
        this.player = player;
        this.numberOfOtherMasts = masts;
    }

    public Player getPlayer() {
        return player;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public int getNumberOfOtherMasts() {
        return numberOfOtherMasts;
    }

    @Override
    public String toString() {
        return "FieldStatus{" +
                "coordinate=" + coordinate +
                ", fieldState=" + fieldState +
                ", player=" + player +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldStatus status = (FieldStatus) o;
        return Objects.equals(coordinate, status.coordinate) &&
                fieldState == status.fieldState &&
                Objects.equals(player, status.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, fieldState, player);
    }
}
