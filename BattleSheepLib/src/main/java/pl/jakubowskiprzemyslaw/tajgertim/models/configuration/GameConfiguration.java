package pl.jakubowskiprzemyslaw.tajgertim.models.configuration;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipAmount;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship.ShipSize;

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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "GameConfiguration{" +
                "gameName='" + gameName + '\'' +
                ", shipConfiguration=" + shipConfiguration +
                '}';
    }
}
