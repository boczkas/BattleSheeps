package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.event.FleetPlacedEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class FleetPlacerQueueListenerTest {
    private Player player;
    private ApplicationEventPublisher publisher;
    private FleetPlacerQueueListener fleetPlacerQueueListener;

    @BeforeMethod
    public void setUp() {
        player = new Player("", "");
        publisher = mock(ApplicationEventPublisher.class);
        LoggerService loggerService = mock(LoggerService.class);

        fleetPlacerQueueListener = new FleetPlacerQueueListener(loggerService, publisher);
    }

    public void listenOnFleetPlacementQueue_getFleetPlacement_triggerEvent() {
        FleetPlacement fleetPlacement = new FleetPlacement(player, new Fleet());
        fleetPlacerQueueListener.listenOnFleetPlacementQueue(fleetPlacement);

        FleetPlacedEvent fleetPlacedEvent = new FleetPlacedEvent(this, fleetPlacement);
        verify(publisher).publishEvent(fleetPlacedEvent);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnFleetPlacementQueue_getObject_throwClassCastException() {
        Object object = new Object();
        fleetPlacerQueueListener.listenOnFleetPlacementQueue((FleetPlacement) object);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnFleetPlacementSizeQueue_getObject_throwClassCastException() {
        Object object = new Object();
        fleetPlacerQueueListener.listenOnFleetPlacementSizeQueue((GameConfiguration) object);
    }


}
