package pl.jakubowskiprzemyslaw.tajgertim;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ResponseBody
@Controller
public class ResultController {

    @GetMapping(value = "/opponent", produces = "text/plain")
    public String getOpponentName() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("10.30.1.140");

        Connection connection = factory.newConnection();

        GetResponse response = null;
        Channel clientChannel = connection.createChannel();
        clientChannel.queueDeclare("przemyslaw0:0:0:0:0:0:0:1", false, false, false, null);
        while (response == null) {
//            if (player.getName() != null)
            response = clientChannel.basicGet("przemyslaw0:0:0:0:0:0:0:1", true);
            if (response != null) {
                System.out.println(new String(response.getBody(), "UTF-8"));
            }
        }
        String name = new String(response.getBody(), "UTF-8");

        clientChannel.close();
        connection.close();

        return name;
    }



}
