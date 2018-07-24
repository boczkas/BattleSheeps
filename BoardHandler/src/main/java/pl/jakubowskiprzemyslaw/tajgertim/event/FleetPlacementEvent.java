package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;

import java.util.Objects;

public class FleetPlacementEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7250195925545653231L;
    private FleetPlacement fleetPlacement;

    public FleetPlacementEvent(Object source, FleetPlacement placement) {
        super(source);
        this.fleetPlacement = placement;
    }

    public FleetPlacement getFleetPlacement() {
        return fleetPlacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FleetPlacementEvent that = (FleetPlacementEvent) o;
        return Objects.equals(fleetPlacement, that.fleetPlacement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fleetPlacement);
    }
}
