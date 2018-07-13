package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Fleet implements Serializable {
    private static final long serialVersionUID = -4101690690207926928L;
    private List<Ship> ships;

    public Fleet(List<Ship> ships) {
        this.ships = ships;
    }

    public Fleet() {
        this(new ArrayList<>());
    }

    public Fleet(Fleet fleet) {
        ships = new ArrayList<>(fleet.getShips());
    }

    List<Ship> getShips() {
        return new ArrayList<>(Collections.unmodifiableCollection(ships));
    }

    Ship getShipAtCoordinate(Coordinate coordinate) {

        final Optional<Ship> shipOnCoordinate = ships.stream()
                .filter(ship -> ship.containsCoordinate(coordinate))
                .findFirst();

        return shipOnCoordinate.orElseGet(Ship::new);
    }

    FieldState getCoordinateStatus(Coordinate coordinate) {

        final Optional<Ship> shipForCoordinate = ships.stream()
                .filter(ship -> ship.containsCoordinate(coordinate))
                .findFirst();

        if(shipForCoordinate.isPresent()){
            Ship ship = shipForCoordinate.get();
            try {
                return ship.getMastFieldState(coordinate);
            } catch (NoMastAtPositionException e) {
                e.printStackTrace();
            }
        }

        return FieldState.EMPTY;
    }
}
