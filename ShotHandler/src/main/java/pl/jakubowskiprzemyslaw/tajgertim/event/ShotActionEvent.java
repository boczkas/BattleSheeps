package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;

import java.util.Objects;

public class ShotActionEvent extends ApplicationEvent {

    private static final long serialVersionUID = -2957601779711767933L;
    private final PlayerAction action;

    public ShotActionEvent(Object source, PlayerAction action) {
        super(source);
        this.action = action;
    }

    PlayerAction getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShotActionEvent that = (ShotActionEvent) o;
        return Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action);
    }
}
