package pl.jakubowskiprzemyslaw.tajgertim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ServerController {

    private Channel channel;
    private Connection connection;
    private Players players = new Players();
    private String queueName = "players";

    ServerController() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("Admin1234@!");
        factory.setHost("10.30.1.140");

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
    }

    void run() throws IOException, TimeoutException {
        ObjectMapper mapper = new ObjectMapper();
        GetResponse response;
        while (!players.arePresent()) {
            response = channel.basicGet(queueName, true);
            if (response != null) {
                String json = new String(response.getBody(), "UTF-8");
                Player player = mapper.readValue(json, Player.class);
                registerPlayer(player);
            }
        }
        putPlayersNamesToQueues();
        closeConnection();
    }

    private void putPlayersNamesToQueues() throws IOException {
        String firstPlayerQueueName = players.getFirstPlayer().obtainQueueName();
        String secondPlayerQueueName = players.getSecondPlayer().obtainQueueName();

        channel.basicPublish("", firstPlayerQueueName, null, ("Opponent's name: " + players.getSecondPlayer().getName() + ". Your move.").getBytes());
        channel.basicPublish("", secondPlayerQueueName, null, ("Opponent's name: " + players.getFirstPlayer().getName()+ ". Not your move").getBytes());
    }

    private void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    private void registerPlayer(Player player) {
        try {
            generateQueueForPlayer(player);
            players.addPlayer(player);
        } catch (IOException | TooManyPlayersException e) {
            e.printStackTrace();
        }
        players.printPlayers();
    }

    private void generateQueueForPlayer(Player player) throws IOException {
        channel.queueDeclare(player.obtainQueueName(), false, false, false, null);
    }
}
