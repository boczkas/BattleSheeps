package pl.jakubowskiprzemyslaw.tajgertim.models;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

@Test
public class PlayerShotsTest {
    private PlayerShots playerShots;

    private static final int COORDINATE_X = 1;
    private static final int COORDINATE_Y = 1;

    private static final int DEFAULT_SIZE_OF_MAP_WITH_ONE_ELEMENT = 1;


    @BeforeMethod
    public void setUp() {
        playerShots = new PlayerShots();
    }

    public void verifyIfAddIncrementsSizeOfMap() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        ShootResult shootResult = ShootResult.HIT;
        playerShots.add(coordinate, shootResult);

        int sizeOfMap = playerShots.getCoordinateShootResultMap().size();
        Assert.assertEquals(DEFAULT_SIZE_OF_MAP_WITH_ONE_ELEMENT, sizeOfMap);
    }

    public void verifyIfAddIncrementsSizeOfMap_WhenShootResultIsNull() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        playerShots.add(coordinate, null);

        int sizeOfMap = playerShots.getCoordinateShootResultMap().size();
        Assert.assertEquals(DEFAULT_SIZE_OF_MAP_WITH_ONE_ELEMENT, sizeOfMap);
    }
}
