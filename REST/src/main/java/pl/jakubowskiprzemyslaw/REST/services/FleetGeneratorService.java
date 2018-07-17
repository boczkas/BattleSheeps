package pl.jakubowskiprzemyslaw.REST.services;

import pl.jakubowskiprzemyslaw.REST.models.EmptyFields;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipAmount;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipSize;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;


import java.util.*;

public class FleetGeneratorService {

    private ShipConfiguration shipConfiguration;
    private int boardSize;
    private EmptyFields emptyFields;

    public FleetGeneratorService() {
        generateDefaultShipConfiguration();
    }

    private void generateDefaultShipConfiguration() { //only for testing
        shipConfiguration = new ShipConfiguration();
        shipConfiguration.put(ShipSize.mast4, new ShipAmount("1"));
        shipConfiguration.put(ShipSize.mast3, new ShipAmount("2"));
        shipConfiguration.put(ShipSize.mast2, new ShipAmount("3"));
        shipConfiguration.put(ShipSize.mast1, new ShipAmount("4"));

        boardSize = shipConfiguration.getBoardSize();
    }

    public Fleet generateRandomFleet() {
        emptyFields = new EmptyFields(boardSize);

        Fleet fleet = new Fleet();
        List<Integer> shipPlacementList = shipConfiguration.getShipPlacementList();

        for (Integer shipLength : shipPlacementList) {
            Optional<Ship> ship = Optional.empty();
            while (!ship.isPresent()) {
                Coordinate randomCoordinate = emptyFields.getRandomCoordinate();
                ship = tryToPlaceShip(shipLength, randomCoordinate);
            }

            fleet.addShip(ship.get());
        }

        return fleet;
    }

    Optional<Ship> tryToPlaceShip(int shipLength, Coordinate coordinate) {
        Ship ship = new Ship();
        List<Coordinate> shipCoordinates = new ArrayList<>();
        shipCoordinates.add(coordinate);
        shipLength--;

        Random random = new Random();
        int coordinateX = random.nextInt(2);
        Coordinate direction = new Coordinate(coordinateX, 1 - coordinateX);

        Coordinate nextCoordinate = new Coordinate(coordinate.getX(), coordinate.getY());
        while (shipLength > 0) {
            nextCoordinate = nextCoordinate.moveCoordinate(direction);
            if (!emptyFields.isEmpty(nextCoordinate)) {
                return Optional.empty();
            }
            shipLength--;
            shipCoordinates.add(nextCoordinate);
        }


        System.out.println(shipCoordinates);

        shipCoordinates
                .forEach(emptyFields::removeCoordinate);
        shipCoordinates.stream()
                .map(Mast::new)
                .forEach(ship::addMastToShip);

        Set<Coordinate> occupiedFields = createBuffer(shipCoordinates);

        occupiedFields
                .forEach(emptyFields::removeCoordinate);

        return Optional.of(ship);
    }

    private Set<Coordinate> createBuffer(List<Coordinate> shipCoordinates) {
        Set<Coordinate> occupiedFields = new HashSet<>();

        for (Coordinate coordinate : shipCoordinates) {
            occupiedFields.addAll(createBuffer(coordinate));
        }

        return occupiedFields;
    }

    private Set<Coordinate> createBuffer(Coordinate coordinate) {
        Set<Coordinate> occupiedFields = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                occupiedFields.add(coordinate.moveCoordinate(new Coordinate(i, j)));
            }
        }
        return occupiedFields;
    }

}
