package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.context.ApplicationEvent;

public class GameRegisteredEvent extends ApplicationEvent {

    private static final long serialVersionUID = -313548090572734951L;

    public GameRegisteredEvent(Object source) {
        super(source);
    }
}
