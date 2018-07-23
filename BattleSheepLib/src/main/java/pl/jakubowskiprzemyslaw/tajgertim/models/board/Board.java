package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    private static final long serialVersionUID = -1760899481776411254L;
    private Fleet fleet;

    public Board(Fleet fleet) {
        this.fleet = fleet;
    }

    public void markHit(Coordinate coordinate) throws NoMastAtPositionException {
        Ship ship = getShipAtCoordinate(coordinate);
        ship.markMastAsHit(coordinate);
    }

    private Ship getShipAtCoordinate(Coordinate coordinate) {
        return fleet.getShipAtCoordinate(coordinate);
    }

    public Fleet getFleet() {
        return new Fleet(fleet);
    }


    public FieldState getCoordinateStatus(Coordinate coordinate) {
        return fleet.getCoordinateStatus(coordinate);
    }

    public List<Ship> getShipList() {
        return fleet.getShips();
    }

    public String getConsoleView(int boardSize) { //for testing only
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j< boardSize; j++) {
                if (fleet.getCoordinateStatus(new Coordinate(j, i)).equals(FieldState.EMPTY)) {
                    stringBuilder.append(".");
                } else {
                    stringBuilder.append("*");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Board{" +
                "fleet=" + fleet +
                '}';
    }
}
