package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

@Test
public class FleetTest {

    private Ship ship;
    private Coordinate coordinate;
    private Fleet fleet;

    @BeforeMethod
    public void setUp() {
        ship = new Ship();
        coordinate = getRandomCoordinate();
        Mast mast = new Mast(coordinate);
        ship.addMastToShip(mast);

        fleet = new Fleet(Arrays.asList(ship));
    }

    private Coordinate getRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Coordinate(x, y);
    }

    @Test(invocationCount = 20)
    public void getExistingShip() {
        Ship shipAtCoordinate = fleet.getShipAtCoordinate(coordinate);

        assertEquals(shipAtCoordinate, ship);
    }

    @Test(invocationCount = 20)
    public void getExistingShipAtNotExistingCoordinate() {
        Coordinate nextCoordinate = coordinate.translate(new Coordinate(0, 1));

        Ship shipAtCoordinate = fleet.getShipAtCoordinate(nextCoordinate);

        assertNotEquals(shipAtCoordinate, ship);
        assertEquals(shipAtCoordinate, new Ship());
    }

    @Test(invocationCount = 20)
    public void getCoordinateStatusForAddedCoordinate() {
        FieldState coordinateStatus = fleet.getCoordinateStatus(coordinate);

        assertEquals(coordinateStatus, FieldState.NOT_HIT_MAST);
    }

    @Test(invocationCount = 20)
    public void getCoordinateStatusForNotAddedCoordinate() {
        Coordinate nextCoordinate = coordinate.translate(new Coordinate(0, 1));
        FieldState coordinateStatus = fleet.getCoordinateStatus(nextCoordinate);

        assertEquals(coordinateStatus, FieldState.EMPTY);
    }

    @Test(invocationCount = 20)
    public void testAddShipToFleet() {
        Fleet fleet = new Fleet();
        fleet.addShip(ship);
        List<Ship> expectedShips = Arrays.asList(ship);

        List<Ship> ships = fleet.getShips();

        assertEquals(ships, expectedShips);
    }


}