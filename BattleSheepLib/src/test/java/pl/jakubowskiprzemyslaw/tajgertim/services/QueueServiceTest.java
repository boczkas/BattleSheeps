package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

import static org.mockito.Mockito.mock;

@Test
public class QueueServiceTest {

    private RabbitTemplate template;
    private QueueService queueService;
    private QueueObject queueObject;
    private String queueName;

    @BeforeMethod
    public void setUp() {
        template = mock(RabbitTemplate.class);
        queueService = new QueueService(template);
        queueObject = mock(QueueObject.class);
        queueName = "queueName";
    }


}
