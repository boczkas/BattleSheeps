package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

import java.io.Serializable;
import java.util.*;

public class ShipConfiguration implements Serializable {

    private static final long serialVersionUID = 8403163905506002218L;

    private Map<ShipSize, ShipAmount> shipConfiguration = new HashMap<>();
    private static final double BOARD_SIZE_RATIO = .2;

    public void put(ShipSize shipSize, ShipAmount shipAmount) {
        shipConfiguration.put(shipSize, shipAmount);
    }

    public int getBoardSize() {
        int fleetSize = getFleetSize();
        double minimalBoardSize = Math.sqrt(fleetSize / BOARD_SIZE_RATIO);
        return (int) Math.ceil(minimalBoardSize);
        //TODO: 31.07 max(boardSize, max(ShipSize))
    }

    public List<Integer> getShipPlacementList() {
        List<Integer> shipPlacementList = new ArrayList<>();

        for (ShipSize shipSize : shipConfiguration.keySet()) {
            shipPlacementList.addAll(creteShipPlacementListForSpecificShipSize(shipSize));
        }

        shipPlacementList.sort((o1, o2) -> o1 < o2? 1: -1);

        return shipPlacementList;
    }

    int getFleetSize() {
        return shipConfiguration.keySet()
                .stream()
                .map(s -> s.size() * shipConfiguration.get(s).getShipAmount())
                .mapToInt(i -> i)
                .sum();
    }

    private List<Integer> creteShipPlacementListForSpecificShipSize(ShipSize shipSize) {
        List<Integer> shipPlacementList = new ArrayList<>();
        int shipAmount = shipConfiguration.get(shipSize).getShipAmount();

        for (int i = 0; i < shipAmount; i++) {
            shipPlacementList.add(shipSize.size());
        }

        return shipPlacementList;
    }

    @Override
    public String toString() {
        return "ShipConfiguration{" +
                "shipConfiguration=" + shipConfiguration +
                '}';
    }
}
