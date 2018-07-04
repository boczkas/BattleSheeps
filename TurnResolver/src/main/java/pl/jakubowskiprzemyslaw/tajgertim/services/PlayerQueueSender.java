package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;

@Component
public class PlayerQueueSender {

  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public PlayerQueueSender(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
    for(int i = 0; i < 4; i++)
      sendMessage();
  }

  private void sendMessage() {
    final Player player = new Player();
    player.setName("jarek");
    player.setIP("127.0.0.1");
    player.setQueueName("jarek123");
    System.out.println("Send message");
    rabbitTemplate.convertAndSend("TurnPlayerQueue", player);
  }
}
