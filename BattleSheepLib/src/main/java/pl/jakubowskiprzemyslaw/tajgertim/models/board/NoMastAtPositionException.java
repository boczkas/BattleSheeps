package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

public class NoMastAtPositionException extends Throwable {
    public NoMastAtPositionException(Coordinate coordinate) {
        super("No Mast at position " + coordinate);
    }
}
