package pl.jakubowskiprzemyslaw.REST.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

  private final RabbitTemplate template;
  private static Logger logger = LoggerFactory.getLogger(QueueService.class);

  @Autowired
  public QueueService(RabbitTemplate template) {
    this.template = template;
  }

  @RabbitHandler
  public void sendMessageToQueue(String queueName, Object message) {
    logger.info("Sending message: " + message + " to queue: " + queueName);
    template.convertAndSend(queueName, message);
  }
}
