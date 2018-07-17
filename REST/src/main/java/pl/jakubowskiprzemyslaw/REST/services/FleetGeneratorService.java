package pl.jakubowskiprzemyslaw.REST.services;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class FleetGeneratorService {

    public static Fleet generateRandomFleet() {
        List<Ship> shipList = new ArrayList<>();
        List<Mast> mastList = new ArrayList<>();

        mastList.add(new Mast(new Coordinate(3, 1)));
        mastList.add(new Mast(new Coordinate(3, 2)));
        mastList.add(new Mast(new Coordinate(3, 3)));
        shipList.add(new Ship(mastList));

        return new Fleet(shipList);
    }

}
