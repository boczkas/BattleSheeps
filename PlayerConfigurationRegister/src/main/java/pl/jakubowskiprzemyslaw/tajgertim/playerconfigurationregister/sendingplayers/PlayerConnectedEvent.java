package pl.jakubowskiprzemyslaw.tajgertim.playerconfigurationregister.sendingplayers;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;

import java.util.Objects;

class PlayerConnectedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 570102554291869796L;
    private final PlayerConfiguration playerConfiguration;

    PlayerConnectedEvent(Object source, PlayerConfiguration playerConfiguration) {
        super(source);
        this.playerConfiguration = playerConfiguration;
    }

    public PlayerConfiguration getPlayerConfiguration() {
        return playerConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerConnectedEvent that = (PlayerConnectedEvent) o;
        return Objects.equals(playerConfiguration, that.playerConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerConfiguration);
    }
}
