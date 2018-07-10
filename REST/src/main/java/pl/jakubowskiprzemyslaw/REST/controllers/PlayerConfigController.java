package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlayerConfigController extends BaseController {

  private String playerConfigQueueName = Queues._1PlayerRegistrationQueue.toString();

  @Autowired
  public PlayerConfigController(QueueService queueService, SessionService sessionService) {
    super(queueService, sessionService);
  }

  @GetMapping(value = "/playerconfig", produces = "text/html")
  public String getGameConfig(Model model) {
    model.addAttribute("player", new Player("", ""));
    return "playerconfig";
  }

  @PostMapping(value = "/playerconfig", produces = "text/html")
  public String sendGameConfig(@ModelAttribute("player") Player player, HttpServletRequest request) {
    String IP = getPlayerIP(request);
    player.setIP("69");
    addObjectToSessionRequest(request, player);
    PlayerConfiguration playerConfiguration = new PlayerConfiguration(player);
    sendObjectToQueue(playerConfigQueueName, playerConfiguration);
    return "redirect:/fleetplacement";
  }

  private String getPlayerIP(HttpServletRequest request) {
    return request.getRemoteAddr();
  }
}