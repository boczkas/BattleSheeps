package pl.jakubowskiprzemyslaw.tajgertim.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.MQConnection;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class PlayerController {

  private Channel channel;
  private String queueName = "players";


  private void openConnection() throws IOException, TimeoutException {
    channel = MQConnection.openConnection();
    channel.queueDeclare(queueName, false, false, false, null);
  }

  private void sendMessage(Player player) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(player);
    channel.basicPublish("", queueName, null, json.getBytes());

  }

  @GetMapping(value = "/player")
  public String greetingForm(Model model) {
    model.addAttribute("player", new Player());
    return "player";
  }

  @PostMapping(value = "/player")
  public String greetingSubmit(@ModelAttribute("player") Player player, HttpServletRequest request) throws IOException, TimeoutException {
    openConnection();
    player.setIP(request.getRemoteAddr());
    sendMessage(player);
    request.getSession().setAttribute("player", player);
    MQConnection.closeConnection(channel);
    return "result";
  }

}
