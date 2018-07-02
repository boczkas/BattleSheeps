package pl.jakubowskiprzemyslaw.tajgertim.configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersContainer;

@Service
public class PlayerQueueReceiver {

  private PlayersContainer playersContainer;

  @Autowired
  public PlayerQueueReceiver(PlayersContainer container) {
    this.playersContainer = container;
  }

  @RabbitListener(queues = "TurnPlayerQueue")
  public void receive(Player player) {
    playersContainer.addPlayer(player);
  }
}
