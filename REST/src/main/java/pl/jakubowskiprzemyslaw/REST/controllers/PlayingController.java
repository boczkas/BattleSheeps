package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Action;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Controller
@SessionAttributes("player")
public class PlayingController extends BaseController {

  @Autowired
  PlayingController(QueueService queueService, SessionService sessionService) {
    super(queueService, sessionService);
  }

  @GetMapping(value = "/playing", produces = "text/html")
  public String getPlaying() {
    return "playing";
  }

  @PostMapping(value = "/playing", produces = "text/plain")
  public String makeShoot(String coordinates, HttpServletRequest request) {

    Object player = request.getSession().getAttribute("Player");
    Coordinate coordinate = returnCoordinates(coordinates);
    Action action = new Shot(coordinate);

    PlayerAction playerAction = new PlayerAction((Player) player, action);
    sendObjectToQueue("PlayingStateMachinePlayerActionQueueTest", playerAction);

    return "playing";
  }

  private Coordinate returnCoordinates(@NotNull String coordinates) {
    String[] split = coordinates.split(",");
    int x = Integer.valueOf(split[0]);
    int y = Integer.valueOf(split[1]);
    return new Coordinate(x, y);
  }
}
