package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import java.io.Serializable;

public class ShipAmount implements Serializable {
    private static final long serialVersionUID = 1059378878515817900L;
    private int shipAmount;

    public ShipAmount(String amount) {
        shipAmount = Integer.parseInt(amount);
    }

    int getShipAmount() {
        return shipAmount;
    }

    @Override
    public String toString() {
        return "ShipAmount{" +
                "shipAmount=" + shipAmount +
                '}';
    }
}
