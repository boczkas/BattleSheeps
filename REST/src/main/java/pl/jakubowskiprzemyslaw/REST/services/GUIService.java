package pl.jakubowskiprzemyslaw.REST.services;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;

import javax.validation.constraints.NotNull;

@Service
public class GUIService {

    public Coordinate translateGUICoordinatesToCoordinate(@NotNull String guiCoordinates) {
        String[] splitCoordinates = guiCoordinates.split(",");
        int x = Integer.valueOf(splitCoordinates[0]);
        int y = Integer.valueOf(splitCoordinates[1]);
        return new Coordinate(x, y);
    }
}
