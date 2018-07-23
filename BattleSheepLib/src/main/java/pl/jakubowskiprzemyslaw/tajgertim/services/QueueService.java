package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;

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
        rabbitAdmin.declareQueue(new Queue(queueName, false, false, false, null));
        template.convertAndSend(queueName, queueObject);
    }

    @RabbitHandler
    public void sendObjectToQueue(Queues queueType, QueueObject queueObject) {
       sendObjectToQueue(queueType.toString(), queueObject);
    }

}
