package pl.jakubowskiprzemyslaw.tajgertim.models.configuration;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipAmount;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipSize;

import java.util.Objects;

public final class GameConfiguration implements QueueObject {

    private static final long serialVersionUID = -6981739379739109856L;

    private String gameName;
    private ShipConfiguration shipConfiguration;

    public GameConfiguration() {
        this.shipConfiguration = new ShipConfiguration();
    }

    public GameConfiguration(FrontGameConfiguration frontGameConfiguration) {
        this.shipConfiguration = new ShipConfiguration();
        this.gameName = frontGameConfiguration.getGameName();
        this.shipConfiguration.put(ShipSize.mast1, new ShipAmount(frontGameConfiguration.getMast1()));
        this.shipConfiguration.put(ShipSize.mast2, new ShipAmount(frontGameConfiguration.getMast2()));
        this.shipConfiguration.put(ShipSize.mast3, new ShipAmount(frontGameConfiguration.getMast3()));
        this.shipConfiguration.put(ShipSize.mast4, new ShipAmount(frontGameConfiguration.getMast4()));
    }

    @Override
    public String toString() {
        return "GameConfiguration{" +
                "gameName='" + gameName + '\'' +
                ", shipConfiguration=" + shipConfiguration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConfiguration that = (GameConfiguration) o;
        return Objects.equals(gameName, that.gameName) &&
                Objects.equals(shipConfiguration, that.shipConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, shipConfiguration);
    }
}
