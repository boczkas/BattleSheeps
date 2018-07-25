package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.event.FleetPlacedEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class FleetPlacerQueueListener {

    private final LoggerService logger;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public FleetPlacerQueueListener(LoggerService logger, ApplicationEventPublisher publisher) {
        this.logger = logger;
        this.publisher = publisher;
    }

    @RabbitListener(queues = "FleetPlacementQueue")
    public void listenOnFleetPlacementQueue(FleetPlacement fleetPlacement) {
        logger.logInfo(FleetPlacerQueueListener.class, "Received message" + fleetPlacement);

        //TODO: 30.07.2018 check if placement is correct

        FleetPlacedEvent event = new FleetPlacedEvent(this, fleetPlacement);
        publisher.publishEvent(event);

    }

    @RabbitListener(queues = "FleetPlacementSizeQueue")
    public void listenOnFleetPlacementSizeQueue(GameConfiguration gameConfiguration) {
        logger.logInfo(FleetPlacerQueueListener.class, "Received message" + gameConfiguration);
        //TODO: 30.07.2018 add rest
    }
}
