package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;

@Component
public class FleetPlacementEventListener implements ApplicationListener<FleetPlacementEvent> {

    private final BoardHandler boardHandler;

    @Autowired
    public FleetPlacementEventListener(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
    }

    @Override
    public void onApplicationEvent(FleetPlacementEvent event) {
        FleetPlacement fleetPlacement = event.getFleetPlacement();
        Player player = fleetPlacement.getPlayer();
        Fleet fleet = fleetPlacement.getFleet();
        boardHandler.addFleetForPlayer(player, fleet);
    }
}
