package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlayerConfigController extends BaseController {

  @Value("${queueName.playerConfig}")
  private String playerConfigQueueName;

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
    addObjectToSessionRequest(request, player);
    sendObjectToQueue(playerConfigQueueName, player);
    return "redirect:/fleetplacement";
  }
}