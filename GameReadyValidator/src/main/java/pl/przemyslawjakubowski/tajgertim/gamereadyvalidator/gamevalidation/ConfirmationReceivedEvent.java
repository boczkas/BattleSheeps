package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.context.ApplicationEvent;

public class ConfirmationReceivedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1573326999276097476L;

    public ConfirmationReceivedEvent(Object source) {
        super(source);
    }
}
