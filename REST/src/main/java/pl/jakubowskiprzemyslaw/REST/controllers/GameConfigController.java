package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.REST.models.GameConfiguration;
import pl.jakubowskiprzemyslaw.REST.queue.QueueSender;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameConfigController {

  private final QueueSender sender;

  @Value("${queue.gameConfig}")
  private String gameConfigQueueName;

  @Autowired
  public GameConfigController(QueueSender sender) {
    this.sender = sender;
  }

  @GetMapping(value = "/gameconfig", produces = "text/html")
  public String getGameConfig(Model model) {
    model.addAttribute("configuration", new GameConfiguration());
    return "gameconfig";
  }

  @PostMapping(value = "/gameconfig", produces = "text/html")
  public String sendGameConfig(@ModelAttribute("configuration") GameConfiguration configuration, HttpServletRequest request) {
    configuration.setIp(request.getRemoteAddr());
    request.getSession().setAttribute("configuration", configuration);
    sender.sendMessageToQueue(gameConfigQueueName, configuration);
    return "playerconfig";
  }

}
