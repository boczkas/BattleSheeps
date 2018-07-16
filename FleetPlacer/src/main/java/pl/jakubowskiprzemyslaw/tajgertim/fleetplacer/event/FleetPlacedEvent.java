package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.event;

import org.springframework.context.ApplicationEvent;

public class FleetPlacedEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6094656042364570599L;

    public FleetPlacedEvent(Object source) {
        super(source);
    }
}
