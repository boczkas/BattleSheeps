package pl.jakubowskiprzemyslaw.tajgertim.services;

import com.rabbitmq.client.GetResponse;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.PlayerRegistrationQueueConnector;
import pl.jakubowskiprzemyslaw.tajgertim.queues.QueueConnector;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class QueueService {
    private final PlayerRegistrationQueueConnector playerQueueConnector;
    private final QueueConnector queueConnector;

    public QueueService(PlayerRegistrationQueueConnector playerQueueConnector, QueueConnector queueConnector) {
        this.playerQueueConnector = playerQueueConnector;
        this.queueConnector = queueConnector;
    }

    public void sendPlayerToQueue(Player player) throws IOException, TimeoutException {
        playerQueueConnector.connectToQueue();
        playerQueueConnector.sendPlayerToQueue(player);
    }

    public String getOpponentNameFromQueue(String playerQueueName) throws IOException, TimeoutException {
        Optional<GetResponse> response = Optional.empty();
        queueConnector.connectToQueue(playerQueueName);

        while (!response.isPresent()) {
            response = Optional.ofNullable(queueConnector.getResponse(playerQueueName));
        }

        return new String(response.get().getBody(), "UTF-8");
    }
}
