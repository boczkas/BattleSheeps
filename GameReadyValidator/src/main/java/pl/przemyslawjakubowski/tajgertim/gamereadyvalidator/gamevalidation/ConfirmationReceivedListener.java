package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FinalConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
class ConfirmationReceivedListener implements ApplicationListener<ConfirmationReceivedEvent> {

    private final QueueService queueService;
    private int counter = 0;

    @Autowired
    ConfirmationReceivedListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(ConfirmationReceivedEvent event) {
        counter++;

        if(counter == 3) {
            Confirmation confirmation = new FinalConfigurationConfirmation();
            queueService.sendObjectToQueue(Queues._8JudgeStartQueue, confirmation);
            counter = 0;
        }
    }
}
