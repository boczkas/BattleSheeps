package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import pl.jakubowskiprzemyslaw.tajgertim.event.FleetPlacementEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerConfigurationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerShootCoordinateEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

@BattleShipQueueInteractionHandler
public class BoardHandlerQueueListener {

    private final BoardHandler boardHandler;
    private final ApplicationEventPublisher publisher;
    private final LoggerService logger;

    public BoardHandlerQueueListener(BoardHandler boardHandler, ApplicationEventPublisher publisher, LoggerService logger) {
        this.publisher = publisher;
        this.boardHandler = boardHandler;
        this.logger = logger;
    }

    @RabbitListener(queues = "BoardHandlerShotQueryQueue")  // 12
    public void listenOnBoardHandlerShotQueryQueue(PlayerShootCoordinate playerShootCoordinate) {
        logger.logInfo(BoardHandlerQueueListener.class, "Received message" + playerShootCoordinate);
        PlayerShootCoordinateEvent event = new PlayerShootCoordinateEvent(this, playerShootCoordinate);
        publisher.publishEvent(event);
    }

    @RabbitListener(queues = "BoardHandlerPlayerQueue") // 6
    void listenOnBoardHandlerPlayerQueue(PlayerConfiguration playerConfiguration) {
        logger.logInfo(BoardHandlerQueueListener.class, playerConfiguration.toString());
        PlayerConfigurationEvent event = new PlayerConfigurationEvent(this, playerConfiguration);
        publisher.publishEvent(event);

    }

    @RabbitListener(queues = "BoardHandlerFleetPlacementQueue") // 7
    void listenOnBoardHandlerFleetPlacementQueue(FleetPlacement fleetPlacement) {
        logger.logInfo(BoardHandlerQueueListener.class, fleetPlacement.toString());
        FleetPlacementEvent event = new FleetPlacementEvent(this, fleetPlacement);
        publisher.publishEvent(event);
    }
}
