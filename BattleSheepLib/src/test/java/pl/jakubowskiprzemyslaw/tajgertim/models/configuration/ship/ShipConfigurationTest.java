package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipConfigurationTest {

    private ShipConfiguration shipConfiguration;

    @BeforeMethod
    public void setUp() {
        shipConfiguration = new ShipConfiguration();
        shipConfiguration.put(ShipSize.mast4, new ShipAmount("1"));
        shipConfiguration.put(ShipSize.mast3, new ShipAmount("2"));
        shipConfiguration.put(ShipSize.mast2, new ShipAmount("3"));
        shipConfiguration.put(ShipSize.mast1, new ShipAmount("4"));
    }

    public void standardShipConfiguration_getFleetSize_return20() {

        assertEquals(shipConfiguration.getFleetSize(), 20);
    }

    public void emptyShipConfiguration_getFleetSize_return0() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();

        assertEquals(shipConfiguration.getFleetSize(), 0);
    }


    public void standardShipConfiguration_getBoardSize_return10() {
        assertEquals(shipConfiguration.getBoardSize(), 10);
    }

    public void emptyShipConfiguration_getBoardSize_return0() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();

        assertEquals(shipConfiguration.getBoardSize(), 0);
    }

    public void oneMast1ShipConfiguration_getBoardSize_return3() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();
        shipConfiguration.put(ShipSize.mast1, new ShipAmount("1"));

        assertEquals(shipConfiguration.getBoardSize(), 3);
    }

    public void testShipPlacementList() {
        List<Integer> expectedShipPlacementList = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
        List<Integer> shipPlacementList = shipConfiguration.getShipPlacementList();

        assertEquals(shipPlacementList, expectedShipPlacementList);
    }

}
