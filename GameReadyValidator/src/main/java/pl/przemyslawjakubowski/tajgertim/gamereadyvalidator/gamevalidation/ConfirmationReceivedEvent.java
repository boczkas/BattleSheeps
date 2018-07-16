package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.context.ApplicationEvent;

class ConfirmationReceivedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1573326999276097476L;

    ConfirmationReceivedEvent(Object source) {
        super(source);
    }
}
