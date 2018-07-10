package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Action;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Controller
public class PlayingController extends BaseController {

  private String queueName = Queues._9PlayingStateMachinePlayerActionQueue.toString();

  @Autowired
  PlayingController(QueueService queueService, SessionService sessionService) {
    super(queueService, sessionService);
  }

  @GetMapping(value = "/playing", produces = "text/html")
  public String getPlaying() {
    return "playing";
  }

  @PostMapping(value = "/playing", produces = "text/plain")
  public void makeShoot(String coordinates, HttpServletRequest request) {
    sendCoordinatesToQueue(coordinates, request);
  }

  private void sendCoordinatesToQueue(String coordinates, HttpServletRequest request) {
    HttpSession session = request.getSession();
    String className = getPlayerClassSimpleName();

    Object player = session.getAttribute(className);
    Coordinate coordinate = returnCoordinates(coordinates);
    Action action = new Shot(coordinate);

    PlayerAction playerAction = new PlayerAction((Player) player, action);
    sendObjectToQueue(queueName, playerAction);
  }

  private Coordinate returnCoordinates(@NotNull String coordinates) {
    String[] split = coordinates.split(",");
    int x = Integer.valueOf(split[0]);
    int y = Integer.valueOf(split[1]);
    return new Coordinate(x, y);
  }

  private String getPlayerClassSimpleName() {
    Class playerClass = Player.class;
    return playerClass.getSimpleName();
  }
}
