package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FleetPlacementConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@Component
public class FleetPlacedEventListener implements ApplicationListener<FleetPlacedEvent> {

    private int amountOfFleetsConfigured = 0;
    private final QueueService queueService;

    @Autowired
    public FleetPlacedEventListener(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void onApplicationEvent(FleetPlacedEvent fleetPlacedEvent) {
        FleetPlacement fleetPlacement = fleetPlacedEvent.getFleetPlacement();

        queueService.sendObjectToQueue(Queues._7BoardHandlerFleetPlacementQueue, fleetPlacement);

        amountOfFleetsConfigured++;
        if (amountOfFleetsConfigured == 2) {
            queueService.sendObjectToQueue(Queues._5GameReadyValidationQueue, new FleetPlacementConfirmation());
            amountOfFleetsConfigured = 0;
        }
    }
}
