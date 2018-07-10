package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OpponentBoardView implements Serializable {
    private static final long serialVersionUID = 3909289998235259708L;
    private Map<Coordinate, ShootResult> fieldView;

    public OpponentBoardView(Map<Coordinate, ShootResult> fieldView) {
        this.fieldView = fieldView;
    }

    public OpponentBoardView() {
        this(new HashMap<>());
    }

    public void put(Coordinate coordinate, ShootResult fieldStateView){
        fieldView.put(coordinate, fieldStateView);
    }

    public ShootResult get(Coordinate coordinate){
        return fieldView.getOrDefault(coordinate, ShootResult.UNKNOWN);
    }

    public Map<Coordinate, ShootResult> getFieldView() {
        return new HashMap<>(fieldView);
    }

    @Override
    public String toString() {
        return "OpponentBoardView{" +
                "fieldView=" + fieldView +
                '}';
    }
}
