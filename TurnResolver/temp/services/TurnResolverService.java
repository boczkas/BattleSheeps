package pl.jakubowskiprzemyslaw.tajgertim.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jakubowskiprzemyslaw.tajgertim.models.Hit;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersContainer;
import pl.jakubowskiprzemyslaw.tajgertim.services.builder.QueueName;
import pl.jakubowskiprzemyslaw.tajgertim.services.builder.RabbitMQConnectionBuilder;
import pl.jakubowskiprzemyslaw.tajgertim.services.builder.RabbitMQQueueImpl;

import java.io.IOException;

public class TurnResolverService extends PlayersContainer {

  private RabbitMQConnector connector;

  private Player currentPlayer;

  public TurnResolverService() {
    super();
  }

  public void run() throws IOException {
    createBuilders();
    connectToQueue();
    resolvePlayers();

    while (true)
      setTurn();
  }



  private void createBuilders() {
    RabbitMQConnectionBuilder builder = new RabbitMQQueueImpl();
    connector = new RabbitMQConnector(builder);
  }

  private void connectToQueue() {
    connector.connect("admin", "Admin1234@!");
  }

  private void resolvePlayers() throws IOException {

    while (getNumberOfPlayers() < 2)
      addPlayerToList();
  }

  private void addPlayerToList() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String playerJSON = connector.getMessage(QueueName.PLAYER.toString());
    Player player = mapper.readValue(playerJSON, Player.class);
    addPlayer(player);

    if(currentPlayer == null)
      currentPlayer = player;

    System.out.println("Current: " + currentPlayer);
  }


  private void setTurn() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String turnJSON = connector.getMessage(QueueName.SHOT.toString());
    if(!turnJSON.equals("")) {
      Hit hit = mapper.readValue(turnJSON, Hit.class);
      changePlayer(hit);
    }
  }

  private void changePlayer(Hit hit) {
    System.out.println("---------");
    System.out.println("Previous result: " + hit.isHit());

    if(!hit.isHit()) {
      currentPlayer = getOppositePlayer();
      notifyPlayer(currentPlayer);
    }

    System.out.println("Current Player: " + currentPlayer.toString());
    System.out.println("--------");
  }

  private Player getOppositePlayer() {
    if(currentPlayer.getName().equals("jarek"))
      return getPlayer("jarek2");
    else
      return getPlayer("jarek");
  }

  private void notifyPlayer(Player player) {
    String queueName = player.getQueueName();
    connector.sendMessage(queueName, "Your turn");
  }

}
