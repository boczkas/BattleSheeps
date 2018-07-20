package pl.jakubowskiprzemyslaw.tajgertim.models.confirmation;

public class FinalConfigurationConfirmation implements Confirmation {
    private static final long serialVersionUID = 35002649524440915L;

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
