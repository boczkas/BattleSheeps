package pl.jakubowskiprzemyslaw.tajgertim.models.coordinates;

import java.io.Serializable;

public class Coordinate implements Serializable {

    private static final long serialVersionUID = 4184173822301899122L;
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
