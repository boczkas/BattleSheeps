package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.REST.models.Game;
import pl.jakubowskiprzemyslaw.REST.models.Player;
import pl.jakubowskiprzemyslaw.REST.services.QueueService;
import pl.jakubowskiprzemyslaw.REST.services.SessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlayerConfigController extends RESTController {

  @Value("${queue.playerConfig}")
  private String playerConfigQueueName;

  @Autowired
  public PlayerConfigController(QueueService queueService, SessionService sessionService) {
    super(queueService, sessionService);
  }

  @GetMapping(value = "/playerconfig", produces = "text/html")
  public String getGameConfig(Model model, HttpServletRequest request) {

    if(!isObjectInSession(request, "Game"))
      return "redirect:/gameconfig";

    model.addAttribute("player", new Player());
    return "playerconfig";
  }

  @PostMapping(value = "/playerconfig", produces = "text/html")
  public String sendGameConfig(@ModelAttribute("player") Player player, HttpServletRequest request) {
    addObjectToSession(request, player);
    sendMessageToQueue(playerConfigQueueName, player);
    return "redirect:/fleetplacement";
  }
}