package pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

public class Shot implements Action {
    private static final long serialVersionUID = 43391618443289671L;
    private final Coordinate coordinate;

    public Shot(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "coordinate=" + coordinate +
                '}';
    }
}
