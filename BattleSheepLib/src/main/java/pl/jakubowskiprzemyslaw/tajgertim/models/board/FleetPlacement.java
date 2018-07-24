package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class FleetPlacement implements QueueObject {

    private static final long serialVersionUID = -4865458408230541324L;
    private Fleet fleet;
    private Player player;

     public FleetPlacement (Player player, Fleet fleet) {
         this.fleet = fleet;
         this.player = player;
     }

    public Fleet getFleet() {
        return fleet;
    }

    public Player getPlayer() {
         return player;
    }

    @Override
    public String toString() {
        return "FleetPlacement{" +
                "fleet=" + fleet +
                ", player=" + player +
                '}';
    }
}
