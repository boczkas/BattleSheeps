package pl.jakubowskiprzemyslaw.tajgertim.configuration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

@Configuration
public class SpringConfiguration {

  private final QueueService queueService;
  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public SpringConfiguration(QueueService queueService, RabbitTemplate rabbitTemplate) {
    this.queueService = queueService;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Bean
  public QueueService getQueueService() {
    return new QueueService(rabbitTemplate);
  }

  @Bean
  public SessionService getSessionService() {
    return new SessionService();
  }

  @Bean
  public LoggerService getLoggerService() { return new LoggerService();}
}
