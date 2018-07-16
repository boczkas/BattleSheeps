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
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;

import java.util.Map;

@Service
public class QueueService {

    private final LoggerService logger;
    private final RabbitTemplate template;
    private RabbitAdmin rabbitAdmin;

    @Autowired
    public QueueService(RabbitTemplate template) {
        this.template = template;
        this.rabbitAdmin = new RabbitAdmin(template);
        logger = new LoggerService();
    }

    @RabbitHandler
    public void sendObjectToQueue(String queueName, QueueObject queueObject) {
        logger.logInfo(QueueService.class, "Sending message: " + queueObject + " to queue: " + queueName);
        boolean DURABLE = false;
        boolean EXCLUSIVE = false;
        boolean AUTO_DELETE = false;
        Map<String, Object> ARGUMENTS = null;

        rabbitAdmin.declareQueue(new Queue(queueName, DURABLE, EXCLUSIVE, AUTO_DELETE, ARGUMENTS));
        template.convertAndSend(queueName, queueObject);
    }

    @RabbitHandler
    public void sendObjectToQueue(Queues queueType, QueueObject queueObject) {
       sendObjectToQueue(queueType.toString(), queueObject);
    }

    @RabbitHandler
    public QueueObject receiveQueueObjectFromQueue(String queueName) {
        return (QueueObject) template.receiveAndConvert(queueName);
    }
}
