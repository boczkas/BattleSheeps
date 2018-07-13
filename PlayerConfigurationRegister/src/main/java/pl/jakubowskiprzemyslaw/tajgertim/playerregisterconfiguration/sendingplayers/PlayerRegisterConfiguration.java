package pl.jakubowskiprzemyslaw.tajgertim.playerregisterconfiguration.sendingplayers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

@Configuration
class PlayerRegisterConfiguration {

  private final RabbitTemplate rabbitTemplate;

  @Autowired
  PlayerRegisterConfiguration(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Bean
  QueueService getQueueService() {
    return new QueueService(rabbitTemplate);
  }

  @Bean
  SessionService getSessionService() {
    return new SessionService();
  }

  @Bean
  LoggerService getLoggerService() { return new LoggerService();}

}
