package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OpponentBoardView implements Serializable {
    private static final long serialVersionUID = 3909289998235259708L;
    private Map<Coordinate, ShootResult> shootingMap;

    public OpponentBoardView(Map<Coordinate, ShootResult> shootingMap) {
        this.shootingMap = shootingMap;
    }

    public void put(Coordinate coordinate, ShootResult fieldStateView){
        shootingMap.put(coordinate, fieldStateView);
    }

    public ShootResult get(Coordinate coordinate){
        return shootingMap.getOrDefault(coordinate, ShootResult.UNKNOWN);
    }

    Map<Coordinate, ShootResult> getShootingMap() {
        return new HashMap<>(shootingMap);
    }

    @Override
    public String toString() {
        return "OpponentBoardView{" +
                "shootingMap=" + shootingMap +
                '}';
    }
}
