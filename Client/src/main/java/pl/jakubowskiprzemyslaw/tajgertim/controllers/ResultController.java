package pl.jakubowskiprzemyslaw.tajgertim.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.MQConnection;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class ResultController {


  @GetMapping(value = "/opponent", produces = "text/plain")
  public @ResponseBody String getOpponentName(HttpServletRequest request) throws IOException, TimeoutException {
    Player player = (Player) request.getSession().getAttribute("player");

    GetResponse response = null;
    Channel clientChannel = MQConnection.openConnection();
    while (response == null) {
      response = clientChannel.basicGet(player.getName() + player.getIP(), true);
      if (response != null) {
        System.out.println(new String(response.getBody(), "UTF-8"));
      }
    }
    String name = new String(response.getBody(), "UTF-8");


    MQConnection.closeConnection(clientChannel);

    return name;
  }
}
