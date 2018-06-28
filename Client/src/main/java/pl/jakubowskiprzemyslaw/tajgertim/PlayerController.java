package pl.jakubowskiprzemyslaw.tajgertim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class PlayerController {

    private Channel channel;
    private Connection connection = null;
    private String queueName = "players";

    private void openConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("10.30.1.140");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    private void sendMessage(Player player) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        channel.queueDeclare(queueName, false, false, false, null);
        String json = mapper.writeValueAsString(player);
        channel.basicPublish("", queueName, null, json.getBytes());

        System.out.println("send: " + json);
    }

    private void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    @GetMapping(value = "/player")
    public String greetingForm(Model model) throws IOException, TimeoutException {
        model.addAttribute("player", new Player());
        return "player";
    }

//    Player player = new Player();

    @PostMapping(value = "/player")
    public String greetingSubmit(@ModelAttribute Player player, HttpServletRequest request) throws IOException, TimeoutException {
        if (connection == null) {
            openConnection();
        }

        player.setIP(request.getRemoteAddr());
//        this.player = player;
        sendMessage(player);

        System.out.println(request.getRemoteAddr());

        closeConnection();

        return "result";
    }

}
