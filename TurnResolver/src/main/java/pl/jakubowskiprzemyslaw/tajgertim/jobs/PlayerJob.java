package pl.jakubowskiprzemyslaw.tajgertim.jobs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersContainer;

@Component
public class PlayerJob {

  private final RabbitListenerEndpointRegistry registry;

  @Autowired
  public PlayerJob(RabbitListenerEndpointRegistry registry) {
    this.registry = registry;
  }

  public void makeTurn(PlayersContainer container) {
    registry.getListenerContainerIds().forEach(System.out::println);
    if (container.getContainerSize() == 2) {
      System.out.println("Loading complete");
      container.clearContainer();

    }
  }

  @RabbitListener(id = "hitListener", autoStartup = "false")
  public void listen() {
  }
}
