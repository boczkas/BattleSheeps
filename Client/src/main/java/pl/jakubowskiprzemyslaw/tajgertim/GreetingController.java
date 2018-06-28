package pl.jakubowskiprzemyslaw.tajgertim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class GreetingController {

    Lista lista = new Lista();
    RestTemplate template = new RestTemplate();

    private Channel channel;
    private Connection connection;
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


        System.out.println("send: " + player.toString());
    }

    private void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    @GetMapping("/player")
    public String greetingForm(Model model) throws IOException, TimeoutException {
        model.addAttribute("player", new Player());

        openConnection();

        return "player";
    }

    @PostMapping("/player")
    public String greetingSubmit(@ModelAttribute Player player, HttpServletRequest request) throws IOException, TimeoutException {

        player.setIP(request.getRemoteAddr());

        sendMessage(player);

        closeConnection();

        System.out.println(request.getRemoteAddr());

//        return "redirect:/player";
        return "result";
    }
}
