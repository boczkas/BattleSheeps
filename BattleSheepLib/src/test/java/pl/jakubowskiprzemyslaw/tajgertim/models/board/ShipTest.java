package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Test
public class ShipTest {

    private Ship ship;
    private Coordinate coordinate;

    @BeforeMethod
    public void setUp() {
        ship = new Ship();
        coordinate = getRandomCoordinate();
        Mast mast = new Mast(coordinate);
        ship.addMastToShip(mast);
    }

    private Coordinate getRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Coordinate(x, y);
    }

    @Test(invocationCount = 20)
    public void checkIfIsNotHit() throws NoMastAtPositionException {
        FieldState fieldState = ship.getMastFieldState(coordinate);

        Assert.assertEquals(fieldState, FieldState.NOT_HIT_MAST);
    }

    @Test(invocationCount = 20)
    public void hitMast_CheckIfIsHit() throws NoMastAtPositionException {
        ship.markMastAsHit(coordinate);

        FieldState fieldState = ship.getMastFieldState(coordinate);

        Assert.assertEquals(fieldState, FieldState.HIT_MAST);
    }

    @Test(invocationCount = 20, expectedExceptions = NoMastAtPositionException.class)
    public void checkIfEmptyShipThrowsNoMastAtPositionException() throws NoMastAtPositionException{
        Ship ship = new Ship();

        FieldState fieldState = ship.getMastFieldState(coordinate);
    }

    @Test(invocationCount = 20, expectedExceptions = NoMastAtPositionException.class)
    public void checkIfShipThrowsNoMastAtPositionException_whenCheckingOtherCoordinate() throws NoMastAtPositionException{
        Coordinate nextCoordinate = coordinate.translate(new Coordinate(0, 1));
        FieldState fieldState = ship.getMastFieldState(nextCoordinate);
    }

    @Test(invocationCount = 20)
    public void checkIfShipContainsMastCoordinate() {
        Assert.assertTrue(ship.containsCoordinate(coordinate));
    }

    @Test(invocationCount = 20)
    public void checkIfShipDoesNotContainOtherCoordinate() {
        Coordinate nextCoordinate = coordinate.translate(new Coordinate(0, 1));

        Assert.assertFalse(ship.containsCoordinate(nextCoordinate));
    }

    @Test(invocationCount = 20)
    public void getListOfCoordinates_checkIfContainsOnlyAddedCoordinate() {
        List<Coordinate> expectedCoordinates = Arrays.asList(coordinate);

        List<Coordinate> coordinates = ship.getCoordinates();

        Assert.assertEquals(coordinates, expectedCoordinates);
    }


}
