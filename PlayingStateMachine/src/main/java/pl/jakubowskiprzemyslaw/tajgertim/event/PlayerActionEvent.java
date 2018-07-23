package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;

import java.util.Objects;

public class PlayerActionEvent extends ApplicationEvent {

    private static final long serialVersionUID = 6950694925484781931L;
    private PlayerAction action;

    public PlayerActionEvent(Object source, PlayerAction action) {
        super(source);
        this.action = action;
    }

    PlayerAction getPlayerAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerActionEvent that = (PlayerActionEvent) o;
        return Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action);
    }
}
