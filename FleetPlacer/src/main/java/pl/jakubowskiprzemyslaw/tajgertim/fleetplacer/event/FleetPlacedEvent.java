package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;

import java.util.Objects;

public class FleetPlacedEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6094656042364570599L;
    private FleetPlacement fleetPlacement;

    public FleetPlacedEvent(Object source, FleetPlacement fleetPlacement) {
        super(source);
        this.fleetPlacement = fleetPlacement;
    }

    FleetPlacement getFleetPlacement() {
        return fleetPlacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FleetPlacedEvent that = (FleetPlacedEvent) o;
        return Objects.equals(fleetPlacement, that.fleetPlacement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fleetPlacement);
    }
}
