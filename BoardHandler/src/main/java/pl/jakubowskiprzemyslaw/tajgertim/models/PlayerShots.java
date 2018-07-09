package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.util.HashMap;
import java.util.Map;

public class PlayerShots {
    private Map<Coordinate, ShootResult> coordinateShootResultMap;

    public PlayerShots() {
        this.coordinateShootResultMap = new HashMap<>();
    }

    public void add(Coordinate coordinate, ShootResult shootResult) {
        coordinateShootResultMap.put(coordinate, shootResult);
    }

    public ShootResult getCoordinateShootResult(Coordinate coordinate){
        return coordinateShootResultMap.get(coordinate);
    }

    public Map<Coordinate, ShootResult> getCoordinateShootResultMap() {
        return new HashMap<>(coordinateShootResultMap);
    }

    @Override
    public String toString() {
        return "PlayerShots{" +
                "coordinateShootResultMap=" + coordinateShootResultMap +
                '}';
    }

}