package pl.jakubowskiprzemyslaw.tajgertim.queues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class PlayerRegistrationQueueConnector extends QueueConnector {

    private String queueName = "players";

    public void connectToQueue() throws IOException, TimeoutException {
        super.connectToQueue(queueName);
    }

    public void sendPlayerToQueue(Player player) throws IOException {
        String json = convertPlayerToJson(player);
        sendMessage(queueName, json);
    }

    private String convertPlayerToJson(Player player) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return convertPlayerToJson(player, mapper);
    }

    private String convertPlayerToJson(Player player, ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(player);
    }
}
