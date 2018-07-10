package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    private static final long serialVersionUID = -1760899481776411254L;
    private List<Ship> shipList;

    public Board(List<Ship> shipList) {
        this.shipList = shipList;
    }

    public void markHit(Coordinate coordinate) throws NoShipAtCoordinateException, NoMastAtPositionException {
        Ship ship = getShipAtCoordinate(coordinate);
        ship.markMastAsHit(coordinate);
    }

    private Ship getShipAtCoordinate(Coordinate coordinate) throws NoShipAtCoordinateException {
        for (Ship ship : shipList) {
            if(ship.containsCoordinate(coordinate)){
                return ship;
            }
        }
        throw new NoShipAtCoordinateException(coordinate);
    }

    public List<Ship> getShipList() {
        return new ArrayList<>(shipList);
    }

    public FieldState getCoordinateStatus(Coordinate coordinate) throws NoMastAtPositionException {
        for (Ship ship : shipList){
            if(ship.containsCoordinate(coordinate)){
                System.out.println("status " + coordinate + "   |   " + ship.getMastState(coordinate));
                return ship.getMastState(coordinate);
            }
        }
        return FieldState.EMPTY;
    }

    @Override
    public String toString() {
        return "Board{" +
                "shipList=" + shipList +
                '}';
    }
}
