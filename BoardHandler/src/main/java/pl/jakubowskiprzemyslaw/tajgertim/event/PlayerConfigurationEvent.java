package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;

import java.util.Objects;

public class PlayerConfigurationEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5794782264095636689L;
    private PlayerConfiguration playerConfiguration;

    public PlayerConfigurationEvent(Object source, PlayerConfiguration configuration) {
        super(source);
        this.playerConfiguration = configuration;
    }

    PlayerConfiguration getPlayerConfiguration() {
        return playerConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerConfigurationEvent that = (PlayerConfigurationEvent) o;
        return Objects.equals(playerConfiguration, that.playerConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerConfiguration);
    }
}
