package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OpponentBoardView implements Serializable {
    private static final long serialVersionUID = 3909289998235259708L;
    private Map<Coordinate, FieldStateView> fieldView;

    public OpponentBoardView(Map<Coordinate, FieldStateView> fieldView) {
        this.fieldView = fieldView;
    }

    public OpponentBoardView() {
        this(new HashMap<>());
    }

    public void put(Coordinate coordinate, FieldStateView fieldStateView){
        fieldView.put(coordinate, fieldStateView);
    }

    public FieldStateView get(Coordinate coordinate){
        return fieldView.getOrDefault(coordinate, FieldStateView.UNKNOWN);
    }

    @Override
    public String toString() {
        return "OpponentBoardView{" +
                "fieldView=" + fieldView +
                '}';
    }
}
