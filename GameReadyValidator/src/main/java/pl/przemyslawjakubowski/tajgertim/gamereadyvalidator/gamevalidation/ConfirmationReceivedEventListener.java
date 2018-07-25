package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FinalConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
class ConfirmationReceivedEventListener implements ApplicationListener<ConfirmationReceivedEvent> {

    private final QueueService queueService;
    private final Confirmations confirmations;

    @Autowired
    ConfirmationReceivedEventListener(QueueService queueService, Confirmations confirmations) {
        this.queueService = queueService;
        this.confirmations = confirmations;
    }

    @Override
    public void onApplicationEvent(ConfirmationReceivedEvent event) {

        confirmations.addConfirmation(event.getConfirmation());

        if (confirmations.areConfirmationsComplete()) {
            Confirmation finalConfigurationConfirmation = new FinalConfigurationConfirmation();
            queueService.sendObjectToQueue(Queues._8JudgeStartQueue, finalConfigurationConfirmation);
            confirmations.clear();
        }
    }
}
