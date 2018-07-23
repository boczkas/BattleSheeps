package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;

import java.util.Objects;

class GameConfiguredEvent extends ApplicationEvent {

    private static final long serialVersionUID = -313548090572734951L;
    private GameConfiguration configuration;

    GameConfiguredEvent(Object source, GameConfiguration gameConfiguration) {
        super(source);
        this.configuration = gameConfiguration;
    }

    GameConfiguration getGameConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConfiguredEvent event = (GameConfiguredEvent) o;
        return Objects.equals(configuration, event.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configuration);
    }
}
