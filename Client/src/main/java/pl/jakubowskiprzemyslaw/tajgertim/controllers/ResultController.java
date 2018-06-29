package pl.jakubowskiprzemyslaw.tajgertim.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.MQConnection;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class ResultController {

    @Autowired
    private MQConnection mqConnection;

    @GetMapping(value = "/opponent", produces = "text/plain")
    public @ResponseBody
    String getOpponentName(HttpServletRequest request) throws IOException, TimeoutException {
        Player player = (Player) request.getSession().getAttribute("player");

        GetResponse response = null;
        Channel clientChannel = mqConnection.openConnection();
        while (response == null) {
            response = clientChannel.basicGet(player.getName() + player.getIP(), true);
            if (response != null) {
                System.out.println(new String(response.getBody(), "UTF-8"));
            }
        }
        String name = new String(response.getBody(), "UTF-8");


        mqConnection.closeConnection(clientChannel);

        return name;
    }
}
