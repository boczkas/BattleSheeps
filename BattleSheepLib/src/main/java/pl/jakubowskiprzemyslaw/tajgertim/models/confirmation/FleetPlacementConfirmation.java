package pl.jakubowskiprzemyslaw.tajgertim.models.confirmation;

public class FleetPlacementConfirmation implements Confirmation {
    private static final long serialVersionUID = 2962962549746137252L;

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
