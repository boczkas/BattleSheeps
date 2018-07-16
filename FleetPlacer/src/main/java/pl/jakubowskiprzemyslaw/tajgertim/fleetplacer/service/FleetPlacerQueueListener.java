package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FleetPlacementConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@BattleShipQueueInteractionHandler
public class FleetPlacerQueueListener {

    private final QueueService queueService;
    private final LoggerService logger;
    private int amountOfFleetsConfigured;

    @Autowired
    public FleetPlacerQueueListener(QueueService queueService, LoggerService logger) {
        this.queueService = queueService;
        this.logger = logger;
        amountOfFleetsConfigured = 0;
    }

    @RabbitListener(queues = "FleetPlacementQueue")
    public void listenOnFleetPlacementQueue(FleetPlacement fleetPlacement) {
        logger.logInfo(FleetPlacerQueueListener.class, "Received message" + fleetPlacement);
        //TODO: 30.07.2018 check if placement is correct
        queueService.sendObjectToQueue(Queues._7BoardHandlerFleetPlacementQueue, fleetPlacement);

        amountOfFleetsConfigured++;
        if (amountOfFleetsConfigured == 2) {
            queueService.sendObjectToQueue(Queues._5GameReadyValidationQueue, new FleetPlacementConfirmation());
            amountOfFleetsConfigured = 0;
        }
    }

    @RabbitListener(queues = "FleetPlacementSizeQueue")
    public void listenOnFleetPlacementQueue(GameConfiguration gameConfiguration) {
        logger.logInfo(FleetPlacerQueueListener.class, "Received message" + gameConfiguration);
        //TODO: 30.07.2018 add rest
    }
}
