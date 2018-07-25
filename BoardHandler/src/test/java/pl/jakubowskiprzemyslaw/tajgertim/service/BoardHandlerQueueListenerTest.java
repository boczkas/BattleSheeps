package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.FleetPlacementEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerConfigurationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.PlayerShootCoordinateEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.PlayerShootCoordinate;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class BoardHandlerQueueListenerTest {

    private ApplicationEventPublisher publisher;
    private BoardHandlerQueueListener listener;

    @BeforeMethod
    public void setUp() {
        LoggerService service = mock(LoggerService.class);
        publisher = mock(ApplicationEventPublisher.class);
        listener = new BoardHandlerQueueListener(publisher, service);
    }

    public void listeningOnBoardHandlerShotQueryQueue_getPlayerShootCoordinate_triggersEvent() {
        PlayerShootCoordinate coordinate = mock(PlayerShootCoordinate.class);
        listener.listenOnBoardHandlerShotQueryQueue(coordinate);
        PlayerShootCoordinateEvent event = new PlayerShootCoordinateEvent(this, coordinate);

        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnShotHandlerPlayerShotQueue_getObject_throwClassCastException() {
        Object object = new Object();
        PlayerShootCoordinateEvent event = new PlayerShootCoordinateEvent(this, (PlayerShootCoordinate) object);

        verify(publisher).publishEvent(event);
    }

    public void listeningOnBoardHandlerPlayerQueue_getPlayerConfiguration_triggersEvent() {
        PlayerConfiguration configuration = mock(PlayerConfiguration.class);
        listener.listenOnBoardHandlerPlayerQueue(configuration);
        PlayerConfigurationEvent event = new PlayerConfigurationEvent(this, configuration);

        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnBoardHandlerPlayerQueue_getObject_throwClassCastException() {
        Object object = new Object();
        PlayerConfigurationEvent event = new PlayerConfigurationEvent(this, (PlayerConfiguration) object);

        verify(publisher).publishEvent(event);
    }

    public void listeningOnBoardHandlerFleetPlacementQueue_getFleetPlacement_triggersEvent() {
        FleetPlacement fleetPlacement = mock(FleetPlacement.class);
        listener.listenOnBoardHandlerFleetPlacementQueue(fleetPlacement);
        FleetPlacementEvent event = new FleetPlacementEvent(this, fleetPlacement);

        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnBoardHandlerFleetPlacementQueue_getObject_throwClassCastException() {
        Object object = new Object();
        FleetPlacementEvent event = new FleetPlacementEvent(this, (FleetPlacement) object);

        verify(publisher).publishEvent(event);
    }
}
