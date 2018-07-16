package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

public class FleetPlacement implements QueueObject {

    private static final long serialVersionUID = -4865458408230541324L;
    private Fleet fleet;

     public FleetPlacement (Fleet fleet) {
         this.fleet = fleet;
     }

    public Fleet getFleet() {
        return fleet;
    }

    @Override
    public String toString() {
        return "FleetPlacement{" +
                "fleet=" + fleet +
                '}';
    }
}
