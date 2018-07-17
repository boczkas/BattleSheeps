package pl.jakubowskiprzemyslaw.REST.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmptyFields {

    List<Coordinate> emptyCoordinates;

    public EmptyFields(int boardSize) {
        emptyCoordinates = new ArrayList<>();
        fillListWithCoordinates(boardSize);
    }

    public Coordinate getRandomCoordinate() {
        Random random = new Random();
        int i = random.nextInt(emptyCoordinates.size());
        return emptyCoordinates.get(i);
    }

    public boolean isEmpty(Coordinate coordinate) {
        return emptyCoordinates.contains(coordinate);
    }

    public void removeCoordinate(Coordinate coordinate) {
        emptyCoordinates.remove(coordinate);
    }

    private void fillListWithCoordinates(int boardSize) {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                emptyCoordinates.add(new Coordinate(i,j));
            }
        }
    }
}
