package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.jobs.PlayerJob;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersContainer;

@Service
public class PlayerQueueReceiver {

  private final PlayersContainer playersContainer;
  private final PlayerJob job;

  @Autowired
  public PlayerQueueReceiver(PlayersContainer container, PlayerJob job) {
    this.playersContainer = container;
    this.job = job;
  }

  @RabbitListener(queues = "TurnPlayerQueue")
  public void receive(Player player) {
    playersContainer.addPlayer(player);
    job.makeTurn(playersContainer);
  }

}
