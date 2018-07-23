package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

public class NoMastAtPositionException extends Throwable {
    private static final long serialVersionUID = -7873852855435342904L;

    NoMastAtPositionException(Coordinate coordinate) {
        super("No Mast at position " + coordinate);
    }
}
