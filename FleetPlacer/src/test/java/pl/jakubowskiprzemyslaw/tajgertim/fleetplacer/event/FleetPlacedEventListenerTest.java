package pl.jakubowskiprzemyslaw.tajgertim.fleetplacer.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FleetPlacementConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.*;

@Test
public class FleetPlacedEventListenerTest {

    private QueueService queueService;
    private FleetPlacedEventListener fleetPlacedEventListener;
    private Player player;

    @BeforeMethod
    public void setUp() {
        player = new Player("", "");
        queueService = mock(QueueService.class);
        fleetPlacedEventListener = new FleetPlacedEventListener(queueService);
    }

    public void triggerEventOnce_sendObjectToQueue_7() {
        FleetPlacement fleetPlacement = new FleetPlacement(player, new Fleet());
        FleetPlacedEvent fleetPlacedEvent = new FleetPlacedEvent(this, fleetPlacement);

        fleetPlacedEventListener.onApplicationEvent(fleetPlacedEvent);

        verify(queueService).sendObjectToQueue(Queues._7BoardHandlerFleetPlacementQueue, fleetPlacement);
    }

    public void triggerEventTwice_sendObjectToQueue_7Twice_sendObjectToQueue_5() {
        FleetPlacement fleetPlacement = new FleetPlacement(player, new Fleet());
        FleetPlacedEvent fleetPlacedEvent = new FleetPlacedEvent(this, fleetPlacement);

        fleetPlacedEventListener.onApplicationEvent(fleetPlacedEvent);
        fleetPlacedEventListener.onApplicationEvent(fleetPlacedEvent);

        verify(queueService, times(2)).sendObjectToQueue(Queues._7BoardHandlerFleetPlacementQueue, fleetPlacement);
        verify(queueService).sendObjectToQueue(Queues._5GameReadyValidationQueue, new FleetPlacementConfirmation());
    }

}
