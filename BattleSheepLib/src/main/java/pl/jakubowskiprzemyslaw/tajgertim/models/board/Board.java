package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    private static final long serialVersionUID = -1760899481776411254L;
    private List<Ship> shipList;

    public Board(List<Ship> shipList) {
        this.shipList = shipList;
    }

    public Board() {
        this(new ArrayList<>());
    }

    public void addShip(Ship ship){
        shipList.add(ship);
    }

    public Ship getShipAtCoordinate(Coordinate coordinate) throws NoShipAtCoordinateException {
        for (Ship ship : shipList) {
            if(ship.containsCoordinate(coordinate)){
                return ship;
            }
        }
        throw new NoShipAtCoordinateException(coordinate);
    }

    @Override
    public String toString() {
        return "Board{" +
                "shipList=" + shipList +
                '}';
    }
}
