package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

@Test
public class CoordinateTest {

    public void bothCoordinatesEqualsToRange_isInRange_returnFalse() {
        Coordinate coordinate = new Coordinate(10,10);
        Assert.assertFalse(coordinate.isLessThan(10));
    }

    public void bothCoordinatesSmallerThanRange_isInRange_returnTrue() {
        Coordinate coordinate = new Coordinate(2,2);
        Assert.assertTrue(coordinate.isLessThan(10));
    }

    public void X_coordinateSmallerThanRange_Y_coordinateBiggerThanRange_isInRange_returnFalse() {
        Coordinate coordinate = new Coordinate(2,4);
        Assert.assertFalse(coordinate.isLessThan(3));
    }

}
