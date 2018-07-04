package pl.jakubowskiprzemyslaw.REST.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

  @Value("${queue.gameConfig}")
  String gameConfigQueueName;

  @Value("${queue.playerConfig}")
  String playerConfigQueueName;

  @Bean
  public Queue getGameConfigQueue() {
    return new Queue(gameConfigQueueName, false, false, false);
  }

  @Bean
  public Queue getPlayerConfigQueue() {
    return new Queue(playerConfigQueueName, false, false, false);
  }

}
