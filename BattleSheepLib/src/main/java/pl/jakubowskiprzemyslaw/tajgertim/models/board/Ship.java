package pl.jakubowskiprzemyslaw.tajgertim.models.board;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    boolean containsCoordinate(Coordinate coordinate) {
        return mastList.contains(new Mast(coordinate));
    }

    public List<Mast> getMastList() {
        return new ArrayList<>(mastList);
    }

    FieldState getMastFieldState(Coordinate coordinate) throws NoMastAtPositionException {
        MastState mastState = getMast(coordinate).getMastState();
        if (mastState.equals(MastState.HIT)) {
            return FieldState.HIT_MAST;
        }
        else{
            return FieldState.NOT_HIT_MAST;
        }
    }

    private Mast getMast(Coordinate coordinate) throws NoMastAtPositionException {

        Optional<Mast> mastAtCoordinate = mastList.stream()
                .filter(mast -> mast.getCoordinate().equals(coordinate))
                .findFirst();

        if(mastAtCoordinate.isPresent()){
            return mastAtCoordinate.get();
        }

        throw new NoMastAtPositionException(coordinate);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "mastList=" + mastList +
                '}';
    }

    void markMastAsHit(Coordinate coordinate) throws NoMastAtPositionException {
        Mast mast = getMast(coordinate);
        mast.markAsHit();
    }
}
