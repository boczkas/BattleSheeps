package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

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

    public FieldState getMastState(Coordinate coordinate) throws NoMastAtPositionException {
        if (getMast(coordinate).getMastState().equals(MastState.HIT)) {
            return FieldState.HIT_MAST;
        }
        if (getMast(coordinate).getMastState().equals(MastState.NOT_HIT)) {
            return FieldState.NOT_HIT_MAST;
        }
        return FieldState.EMPTY;
    }

    private Mast getMast(Coordinate coordinate) throws NoMastAtPositionException {
        for (Mast mast: mastList) {
            if (mast.getCoordinate().equals(coordinate)) {
                return mast;
            }
        }
        throw new NoMastAtPositionException(coordinate);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "mastList=" + mastList +
                '}';
    }

    public void markMastAsHit(Coordinate coordinate) throws NoMastAtPositionException {
        Mast mast = getMast(coordinate);
        mast.markAsHit();
    }
}
