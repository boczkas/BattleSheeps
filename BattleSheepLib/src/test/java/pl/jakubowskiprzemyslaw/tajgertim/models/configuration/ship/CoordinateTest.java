package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

@Test
public class CoordinateTest {

    public void coordinate10_10_isInRange10_returnFalse() {
        Coordinate coordinate = new Coordinate(10,10);
        Assert.assertFalse(coordinate.isInRange(10));
    }

    public void coordinate2_2_isInRange10_returnTrue() {
        Coordinate coordinate = new Coordinate(2,2);
        Assert.assertTrue(coordinate.isInRange(10));
    }

    public void coordinate2_4_isInRange3_returnFalse() {
        Coordinate coordinate = new Coordinate(2,4);
        Assert.assertFalse(coordinate.isInRange(3));
    }

}
