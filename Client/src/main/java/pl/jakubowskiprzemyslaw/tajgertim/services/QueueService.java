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
//        playerQueueConnector.closeConnection();   //TODO: 13.07.2018 - connection is not closed, because rzuca bledami
    }

    public String getOpponentNameFromQueue(String playerQueueName) throws IOException, TimeoutException {
        Optional<GetResponse> response = Optional.empty();
        queueConnector.connectToQueue(playerQueueName);

        while (!response.isPresent()) {
            response = Optional.ofNullable(queueConnector.getResponse(playerQueueName));
        }

        System.out.println(new String(response.get().getBody(), "UTF-8")); // TODO: 16.07.2018 to be replaced with logger
        //        queueConnector.closeConnection(); //TODO: 13.07.2018 - connection is not closed, because rzuca bledami

        return new String(response.get().getBody(), "UTF-8");
    }
}
