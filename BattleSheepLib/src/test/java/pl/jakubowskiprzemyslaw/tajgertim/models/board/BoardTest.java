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
    public void whenShotIsPerformedOnCoordinate_fieldStateAtCoordinateEquals_HIT_MAST() throws NoMastAtPositionException {
        board.markHit(coordinate);

        FieldState fieldState = board.getCoordinateStatus(coordinate);

        Assert.assertEquals(fieldState, FieldState.HIT_MAST);
    }

    @Test(expectedExceptions = NoMastAtPositionException.class)
    public void markHitOnNotExistingField() throws NoMastAtPositionException {
        Coordinate nextCoordinate = coordinate.translate(new Coordinate(1, 0));
        board.markHit(nextCoordinate);
    }

    @Test
    public void checkConsoleView() {
        Coordinate coordinate = new Coordinate(1,2);
        Board board = createBoardWithFleetOnCoordinate(coordinate);

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

        int boardSize = 10;
        String consoleView = board.getConsoleView(boardSize);

        assertEquals(consoleView, expectedView);

    }

    private Board createBoardWithFleetOnCoordinate(Coordinate coordinate) {
        Ship ship = new Ship();
        ship.addMastToShip(new Mast(coordinate));
        Fleet fleet = new Fleet(Arrays.asList(ship));
        return new Board(fleet);
    }

}