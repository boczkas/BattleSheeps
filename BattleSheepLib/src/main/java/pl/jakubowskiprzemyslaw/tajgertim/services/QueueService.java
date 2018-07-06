package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

@Service
public class QueueService {

  private final RabbitTemplate template;
  private static final Logger logger = LoggerFactory.getLogger(QueueService.class);

  @Autowired
  public QueueService(RabbitTemplate template) {
    this.template = template;
  }

  @RabbitHandler
  public void sendObjectToQueue(String queueName, QueueObject queueObject) {
    logger.info("Sending message: " + queueObject + " to queue: " + queueName);
    template.convertAndSend(queueName, queueObject);
  }

  @RabbitHandler
  public QueueObject receiveQueueObjectFromQueue(String queueName){
    return (QueueObject) template.receiveAndConvert(queueName);
  }
}
