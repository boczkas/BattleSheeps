package pl.jakubowskiprzemyslaw.REST.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.OpponentBoardView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.PlayerBoardView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BoardsHandlerController {

  private final BoardsViewHandler handler;

  public BoardsHandlerController(BoardsViewHandler handler) {
    this.handler = handler;
  }

  @PostMapping(value = "/getmyboard")
  public @ResponseBody
  ObjectNode getMyBoard() {
    ObjectMapper mapper = new ObjectMapper();
    Optional<BoardsView> view = handler.getView();

    if (view.isPresent()) {

      ObjectNode node = mapper.createObjectNode();

      BoardsView boardsView = view.get();
      List<Ship> shipList = boardsView.getShipList();

      shipList.forEach(ship -> {
                List<Mast> mastList = ship.getMastList();

                AtomicInteger x = new AtomicInteger();
                AtomicInteger y = new AtomicInteger();

                mastList.forEach(mast -> {
                  x.set(mast.getCoordinate().getX());
                  y.set(mast.getCoordinate().getY());
                  node.put("cell" + x + y, "X");

                  }
                );
              }
      );
      return node;
    }
    return mapper.createObjectNode();
  }

  @PostMapping(value = "/getopponentboard")
  public @ResponseBody
  ObjectNode getOpponentBoard() {
    Optional<BoardsView> view = handler.getView();
    ObjectMapper mapper = new ObjectMapper();

    if (view.isPresent()) {
      ObjectNode node = mapper.createObjectNode();

      BoardsView boardsView = view.get();
      Map<Coordinate, ShootResult> board = boardsView.getFieldView();

      for (Map.Entry<Coordinate, ShootResult> field: board.entrySet()) {

        node.put("opp_cell" + field.getKey().getX() + field.getKey().getY(), field.getValue().name());

      }

      handler.clear();
      return node;
    }

    return mapper.createObjectNode();
  }
}
