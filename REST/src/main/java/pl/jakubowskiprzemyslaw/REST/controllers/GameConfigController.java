package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.REST.models.GameConfigration;
import pl.jakubowskiprzemyslaw.REST.services.QueueService;
import pl.jakubowskiprzemyslaw.REST.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class GameConfigController extends BaseController {

  @Value("${queueName.gameConfig}")
  private String gameConfigQueueName;

  @Autowired
  public GameConfigController(QueueService queueService, SessionService sessionService) {
    super(queueService, sessionService);
  }

  @GetMapping(value = "/gameconfig", produces = "text/html")
  public String getGameConfig(Model model) {
    model.addAttribute("configuration", new GameConfigration());
    return "gameconfig";
  }

  @PostMapping(value = "/gameconfig", produces = "text/html")
  public String sendGameConfig(@Valid @ModelAttribute("configuration") GameConfigration configuration, HttpServletRequest request) {
    addObjectToSessionRequest(request, configuration);
    sendObjectToQueue(gameConfigQueueName, configuration);
    return "redirect:/playerconfig";
  }
}