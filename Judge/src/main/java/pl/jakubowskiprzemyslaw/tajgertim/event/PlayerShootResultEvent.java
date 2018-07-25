package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.service.JudgeQueueListener;

import java.io.Serializable;
import java.util.Objects;

public class PlayerShootResultEvent extends ApplicationEvent {

    private static final long serialVersionUID = 327504315174027883L;
    private final PlayerShootResult playerShootResult;

    public PlayerShootResultEvent(Object object, PlayerShootResult playerShootResult) {
        super(object);
        this.playerShootResult = playerShootResult;
    }

    PlayerShootResult getPlayerShootResult() {
        return playerShootResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerShootResultEvent that = (PlayerShootResultEvent) o;
        return Objects.equals(playerShootResult, that.playerShootResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerShootResult);
    }
}
