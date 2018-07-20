package pl.jakubowskiprzemyslaw.tajgertim.models.confirmation;

public class PlayerConfigurationConfirmation implements Confirmation {
    private static final long serialVersionUID = 4893537338324481980L;

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
