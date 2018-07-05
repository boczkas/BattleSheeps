package pl.jakubowskiprzemyslaw.REST.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

  @Value("${queueName.gameConfig}")
  String gameConfigQueueName;

  @Value("${queueName.playerConfig}")
  String playerConfigQueueName;

  private final boolean durable = false;
  private final boolean exclusive = false;
  private final boolean autoDelete = false;

  @Bean
  public Queue getGameConfigQueue() {
    return new Queue(gameConfigQueueName, durable, exclusive, autoDelete);
  }

  @Bean
  public Queue getPlayerConfigQueue() {
    return new Queue(playerConfigQueueName, durable, exclusive, autoDelete);
  }

}
