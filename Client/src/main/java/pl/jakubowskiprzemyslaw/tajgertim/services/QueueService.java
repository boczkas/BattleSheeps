package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.PlayerRegistrationQueueConnector;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class QueueService {
    private PlayerRegistrationQueueConnector queueConnector;

    public QueueService(PlayerRegistrationQueueConnector queueConnector) {
        this.queueConnector = queueConnector;
    }

    public void sendPlayerToQueue(Player player) throws IOException, TimeoutException {
        queueConnector.connectToQueue();
        queueConnector.sendPlayerToQueue(player);
//        playerQueueConnector.closeConnection();   //TODO: 13.07.2018 - connection is not closed, because rzuca bledami
    }
}
