package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.FieldStateView;
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
  String getMyBoard() {
    Optional<BoardsView> view = handler.getView();

    if (view.isPresent()) {

      StringBuilder builder = new StringBuilder(" { ");


      BoardsView boardsView = view.get();
      PlayerBoardView playerBoardView = boardsView.getPlayerBoardView();
      Board board = playerBoardView.getBoard();
      List<Ship> shipList = board.getShipList();
      shipList.forEach(ship -> {
                List<Mast> mastList = ship.getMastList();

                AtomicInteger x = new AtomicInteger();
                AtomicInteger y = new AtomicInteger();


                mastList.forEach(mast -> {
                  x.set(mast.getCoordinate().getX());
                  y.set(mast.getCoordinate().getY());

                  builder.append("\"cell").append(x.get()).append(y.get()).append("\" : \"X\", ");

                  }
                );

              }
      );

      String tmp= builder.toString().trim().substring(0, builder.toString().length() - 3);

      tmp += " }";
      return tmp;
    }

    return "{}";
  }

  @PostMapping(value = "/getopponentboard")
  public @ResponseBody
  String getOpponentBoard() {
    Optional<BoardsView> view = handler.getView();

    if (view.isPresent()) {

      StringBuilder builder = new StringBuilder(" { ");


      BoardsView boardsView = view.get();
      OpponentBoardView opponentBoardView = boardsView.getOpponentBoard();
      Map<Coordinate, FieldStateView> board = opponentBoardView.getFieldView();

      for (Map.Entry<Coordinate, FieldStateView> field: board.entrySet()) {
        builder.append("\"opp_cell").append(field.getKey().getX()).append(field.getKey().getY()).append("\" : \"").append(field.getValue().name()).append("\"");
        builder.append(", ");
      }

      String tmp= builder.toString().trim().substring(0, builder.toString().length() - 3);

      tmp += " }";

      handler.clear();

      return tmp;
    }

    return "{}";
  }
}
