package pl.jakubowskiprzemyslaw.tajgertim.models.confirmation;

public class GameConfigurationConfirmation implements Confirmation {
    private static final long serialVersionUID = -3421689741413565442L;

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
