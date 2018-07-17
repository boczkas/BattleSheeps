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

    @Override
    public String toString() {
        return "Board{" +
                "fleet=" + fleet +
                '}';
    }

    public List<Ship> getShipList() {
        return fleet.getShips();
    }

    public void drawBoardForBoardSize(int boardSize) { //for testing only
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j< boardSize; j++) {
                if (fleet.getCoordinateStatus(new Coordinate(i, j)).equals(FieldState.EMPTY)) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
