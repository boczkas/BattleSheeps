package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.context.ApplicationEvent;

class GameConfiguredEvent extends ApplicationEvent {

    private static final long serialVersionUID = -313548090572734951L;

    GameConfiguredEvent(Object source) {
        super(source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
