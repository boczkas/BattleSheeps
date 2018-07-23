package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

public class BoardTest {

    private Ship ship;
    private Coordinate coordinate;
    private Fleet fleet;
    private Board board;

    @BeforeMethod
    public void setUp() {
        ship = new Ship();
        coordinate = getRandomCoordinate();
        Mast mast = new Mast(coordinate);
        ship.addMastToShip(mast);

        fleet = new Fleet(Arrays.asList(ship));

        board = new Board(fleet);
    }

    private Coordinate getRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Coordinate(x, y);
    }

    @Test(invocationCount = 20)
    public void markHit_checkIfIsHit() throws NoMastAtPositionException {
        board.markHit(coordinate);

        FieldState fieldState = board.getCoordinateStatus(coordinate);

        Assert.assertEquals(fieldState, FieldState.HIT_MAST);
    }

    @Test(invocationCount = 20, expectedExceptions = NoMastAtPositionException.class)
    public void markHitOnNotExistingField() throws NoMastAtPositionException {
        Coordinate nextCoordinate = coordinate.moveCoordinate(new Coordinate(1, 0));
        board.markHit(nextCoordinate);
    }

    @Test(invocationCount = 20)
    public void getShipList() {
        List<Ship> expectedShipList = Arrays.asList(ship);
        List<Ship> shipList = board.getShipList();

        assertEquals(shipList, expectedShipList);
    }

    @Test
    public void checkConsoleView() {
        Coordinate coordinate = new Coordinate(1,2);
        Ship ship = new Ship();
        ship.addMastToShip(new Mast(coordinate));
        Fleet fleet = new Fleet(Arrays.asList(ship));
        Board board = new Board(fleet);

        String expectedView =
                "..........\n" +
                "..........\n" +
                ".*........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n";

        String consoleView = board.getConsoleView(10);

        assertEquals(consoleView, expectedView);

    }

}