package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShipConfiguration implements Serializable {

    private static final long serialVersionUID = 8403163905506002218L;

    private Map<ShipSize, ShipAmount> shipConfiguration = new HashMap<>();

    public void put(ShipSize shipSize, ShipAmount shipAmount) {
        shipConfiguration.put(shipSize, shipAmount);
    }

    @Override
    public String toString() {
        return "ShipConfiguration{" +
                "shipConfiguration=" + shipConfiguration +
                '}';
    }
}
