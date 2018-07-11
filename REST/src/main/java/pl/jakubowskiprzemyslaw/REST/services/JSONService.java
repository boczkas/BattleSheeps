package pl.jakubowskiprzemyslaw.REST.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class JSONService {

    private final ObjectMapper mapper;

    @Autowired
    public JSONService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectNode createJSONNodeForPlayerShots(BoardsView boardsView) {
        ObjectNode node = mapper.createObjectNode();
        Map<Coordinate, ShootResult> board = boardsView.getShootingMap();
        createJSONWithFields(node, board);
        return node;
    }

    public ObjectNode createJSONNodeWithShips(BoardsView boardsView) {
        ObjectNode node = mapper.createObjectNode();
        List<Ship> shipList = boardsView.getShipList();
        shipList.forEach(ship -> createJSONWithMasts(node, ship));
        return node;
    }

    private void createJSONWithMasts(ObjectNode node, Ship ship) {
        List<Mast> mastList = ship.getMastList();
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();

        mastList.forEach(mast -> {
            int[] coordinates = getMastCoordinates(mast);
            x.set(coordinates[0]);
            y.set(coordinates[1]);
            node.put("cell" + x + y, "X");
        });
    }

    private void createJSONWithFields(ObjectNode node, Map<Coordinate, ShootResult> board) {
        Coordinate coordinate;
        ShootResult shootResult;
        int coordinateX;
        int coordinateY;

        for (Map.Entry<Coordinate, ShootResult> field : board.entrySet()) {
            coordinate = field.getKey();
            coordinateX = coordinate.getX();
            coordinateY = coordinate.getY();
            shootResult = field.getValue();
            node.put("opp_cell" + coordinateX + coordinateY, shootResult.toString());
        }
    }

    private int[] getMastCoordinates(Mast mast) {
        Coordinate coordinate = mast.getCoordinate();
        int coordinateX = coordinate.getX();
        int coordinateY = coordinate.getY();

        return new int[]{coordinateX, coordinateY};
    }
}
