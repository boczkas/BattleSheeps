package pl.jakubowskiprzemyslaw.tajgertim.models.coordinates;

import java.io.Serializable;
import java.util.Objects;

public class Coordinate implements Serializable {

    private static final long serialVersionUID = 4184173822301899122L;
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate translate(Coordinate coordinate) {
        return new Coordinate(x + coordinate.x, y + coordinate.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInRange(int range) {
        return this.x < range && this.y < range;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
