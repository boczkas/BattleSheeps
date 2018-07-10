package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

@Service
public class QueueService {

    private static final Logger logger = LoggerFactory.getLogger(QueueService.class);
    private final RabbitTemplate template;
    private RabbitAdmin rabbitAdmin;

    @Autowired
    public QueueService(RabbitTemplate template) {
        this.template = template;
        this.rabbitAdmin = new RabbitAdmin(template);
    }

    @RabbitHandler
    public void sendObjectToQueue(String queueName, QueueObject queueObject) {
        logger.info("Sending message: " + queueObject + " to queue: " + queueName);
        rabbitAdmin.declareQueue(new Queue(queueName, false, false, false, null));
        template.convertAndSend(queueName, queueObject);
    }

    @RabbitHandler
    public QueueObject receiveQueueObjectFromQueue(String queueName) {
        return (QueueObject) template.receiveAndConvert(queueName);
    }
}
