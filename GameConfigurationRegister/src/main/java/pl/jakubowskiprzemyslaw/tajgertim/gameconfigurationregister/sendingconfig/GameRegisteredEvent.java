package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.context.ApplicationEvent;

class GameRegisteredEvent extends ApplicationEvent {

    private static final long serialVersionUID = -313548090572734951L;

    GameRegisteredEvent(Object source) {
        super(source);
    }
}
