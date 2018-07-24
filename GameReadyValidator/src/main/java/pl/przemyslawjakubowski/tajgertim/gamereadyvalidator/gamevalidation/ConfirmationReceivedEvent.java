package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;

import java.util.Objects;

class ConfirmationReceivedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1573326999276097476L;
    private final Confirmation confirmation;

    ConfirmationReceivedEvent(Object source, Confirmation confirmation) {
        super(source);
        this.confirmation = confirmation;
    }

    Confirmation getConfirmation() {
        return confirmation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmationReceivedEvent that = (ConfirmationReceivedEvent) o;
        return Objects.equals(confirmation, that.confirmation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmation);
    }
}
