package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FinalConfigurationConfirmation;

public class FinalConfigurationConfirmationEvent extends ApplicationEvent {

    private static final long serialVersionUID = -1217083920916298297L;
    private final FinalConfigurationConfirmation finalConfigurationConfirmation;

    public FinalConfigurationConfirmationEvent(Object source, FinalConfigurationConfirmation finalConfigurationConfirmation) {
        super(source);
        this.finalConfigurationConfirmation = finalConfigurationConfirmation;
    }

    public FinalConfigurationConfirmation getFinalConfigurationConfirmation() {
        return finalConfigurationConfirmation;
    }
}
