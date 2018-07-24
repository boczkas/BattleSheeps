package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

public class NoShipAtCoordinateException extends Throwable {
    private static final long serialVersionUID = 5086766998434528179L;

    public NoShipAtCoordinateException(Coordinate coordinate) {
        super("No ship at coordinate: \n" + coordinate);
    }
}
