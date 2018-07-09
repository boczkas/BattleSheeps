package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.io.Serializable;
import java.util.Objects;

public class Mast implements Serializable {
    private static final long serialVersionUID = -2770152303461743352L;
    private Coordinate coordinate;
    private MastState mastState;

    public Mast(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.mastState = MastState.NOT_HIT;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public MastState getMastState() {
        return mastState;
    }

    public void markAsHit() {
        mastState = MastState.HIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mast mast = (Mast) o;
        return Objects.equals(coordinate, mast.coordinate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "Mast{" +
                "coordinate=" + coordinate +
                ", mastState=" + mastState +
                '}';
    }
}
