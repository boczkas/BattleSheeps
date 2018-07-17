package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ShipConfigurationTest {

    public void standardShipConfiguration_getFleetSize_return20() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();
        shipConfiguration.put(ShipSize.mast4, new ShipAmount("1"));
        shipConfiguration.put(ShipSize.mast3, new ShipAmount("2"));
        shipConfiguration.put(ShipSize.mast2, new ShipAmount("3"));
        shipConfiguration.put(ShipSize.mast1, new ShipAmount("4"));

        Assert.assertEquals(shipConfiguration.getFleetSize(), 20);
    }

    public void emptyShipConfiguration_getFleetSize_return0() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();

        Assert.assertEquals(shipConfiguration.getFleetSize(), 0);
    }


    public void standardShipConfiguration_getBoardSize_return10() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();
        shipConfiguration.put(ShipSize.mast4, new ShipAmount("1"));
        shipConfiguration.put(ShipSize.mast3, new ShipAmount("2"));
        shipConfiguration.put(ShipSize.mast2, new ShipAmount("3"));
        shipConfiguration.put(ShipSize.mast1, new ShipAmount("4"));

        Assert.assertEquals(shipConfiguration.getBoardSize(), 10);
    }

    public void emptyShipConfiguration_getBoardSize_return0() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();

        Assert.assertEquals(shipConfiguration.getBoardSize(), 0);
    }

    public void oneMast1ShipConfiguration_getBoardSize_return3() {
        ShipConfiguration shipConfiguration = new ShipConfiguration();
        shipConfiguration.put(ShipSize.mast1, new ShipAmount("1"));

        Assert.assertEquals(shipConfiguration.getBoardSize(), 3);
    }

}
