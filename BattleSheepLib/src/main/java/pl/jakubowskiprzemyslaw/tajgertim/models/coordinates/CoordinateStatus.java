package pl.jakubowskiprzemyslaw.tajgertim.models.coordinates;


import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class CoordinateStatus implements QueueObject {
    private Coordinate coordinate;
    private CoordinateState coordinateState;
    private Player player;

    public CoordinateStatus() {
        this.coordinate = new Coordinate();
        this.coordinateState = CoordinateState.EMPTY;
        this.player = new Player();
    }

    public CoordinateStatus(Coordinate coordinate, CoordinateState coordinateState, Player player) {
        this.coordinate = coordinate;
        this.coordinateState = coordinateState;
        this.player = player;
    }

    @Override
    public String toString() {
        return "CoordinateStatus{" +
                "coordinate=" + coordinate +
                ", coordinateState=" + coordinateState +
                ", player=" + player +
                '}';
    }
}
