package pl.jakubowskiprzemyslaw.REST.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BoardsHandlerController {

    private final BoardsViewHandler handler;
    private ObjectMapper mapper;
    private ObjectNode node;
    private BoardsView boardsView;

    public BoardsHandlerController(BoardsViewHandler handler) {
        this.handler = handler;
        this.mapper = new ObjectMapper();
    }

    @PostMapping(value = "/getmyboard")
    public @ResponseBody
    ObjectNode getMyBoard() {
        Optional<BoardsView> view = handler.getView();

        if (view.isPresent()) {
            node = mapper.createObjectNode();
            boardsView = view.get();
            List<Ship> shipList = boardsView.getShipList();
            shipList.forEach(ship -> createJSONWithMasts(node, ship));
            return node;
        }

        return mapper.createObjectNode();
    }

    @PostMapping(value = "/getopponentboard")
    public @ResponseBody
    ObjectNode getOpponentBoard() {
        Optional<BoardsView> view = handler.getView();

        if (view.isPresent()) {
            node = mapper.createObjectNode();
            boardsView = view.get();
            Map<Coordinate, ShootResult> board = boardsView.getFieldView();
            createJSONWithFields(node, board);
            handler.clear();
            return node;
        }

        return mapper.createObjectNode();
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
