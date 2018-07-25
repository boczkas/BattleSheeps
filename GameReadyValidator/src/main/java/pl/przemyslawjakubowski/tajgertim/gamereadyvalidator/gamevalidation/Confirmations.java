package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Confirmations {

    private Set<Class> confirmations;

    Confirmations() {
        confirmations = new HashSet<>();
    }

    void addConfirmation(Confirmation confirmation) {
        confirmations.add(confirmation.getClass());
    }

    boolean areConfirmationsComplete() {
        return confirmations.size() == 3;
    }

    void clear() {
        confirmations = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Confirmations that = (Confirmations) o;
        return Objects.equals(confirmations, that.confirmations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmations);
    }
}
