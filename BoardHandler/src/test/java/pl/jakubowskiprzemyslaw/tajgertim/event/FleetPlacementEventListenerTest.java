package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class FleetPlacementEventListenerTest {

    private FleetPlacementEventListener listener;
    private BoardHandler boardHandler;

    @BeforeMethod
    public void setUp() {
        boardHandler = mock(BoardHandler.class);
        listener = new FleetPlacementEventListener(boardHandler);
    }

    public void sendingFleetPlacement_AddsFleetForPlayer() {
        Player player = new Player("", "");
        Fleet fleet = mock(Fleet.class);
        FleetPlacement placement = new FleetPlacement(player, fleet);
        FleetPlacementEvent fleetPlacementEvent = new FleetPlacementEvent(this, placement);
        listener.onApplicationEvent(fleetPlacementEvent);

        verify(boardHandler).addFleetForPlayer(player, fleet);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object object = new Object();
        FleetPlacementEvent event = new FleetPlacementEvent(this, (FleetPlacement) object);
        listener.onApplicationEvent(event);
    }
}
