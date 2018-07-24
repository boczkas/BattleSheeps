package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;

import java.util.Objects;

public class PlayerShootCoordinateEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1309344727945828827L;
    private PlayerShootCoordinate coordinate;

    public PlayerShootCoordinateEvent(Object source, PlayerShootCoordinate coordinate) {
        super(source);
        this.coordinate = coordinate;
    }

    PlayerShootCoordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerShootCoordinateEvent that = (PlayerShootCoordinateEvent) o;
        return Objects.equals(coordinate, that.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
