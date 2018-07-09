package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ship implements Serializable {
    private static final long serialVersionUID = 3450985123295669350L;
    private List<Mast> mastList;

    public Ship(List<Mast> mastList) {
        this.mastList = mastList;
    }

    public Ship() {
        this(new ArrayList<>());
    }

    public void addMastToShip(Mast mast){
        mastList.add(mast);
    }

    public boolean containsCoordinate(Coordinate coordinate) {
        return mastList.contains(new Mast(coordinate));
    }

    public List<Mast> getMastList() {
        return new ArrayList<>(mastList);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "mastList=" + mastList +
                '}';
    }
}
