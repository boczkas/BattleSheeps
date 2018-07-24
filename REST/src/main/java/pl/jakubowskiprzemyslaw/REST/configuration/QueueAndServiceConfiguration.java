package pl.jakubowskiprzemyslaw.REST.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

@Configuration
public class QueueAndServiceConfiguration {

    private final RabbitTemplate template;

    @Autowired
    public QueueAndServiceConfiguration(RabbitTemplate template) {
        this.template = template;
    }

    @Bean
    public QueueService getQueueService() {
        return new QueueService(template);
    }

    @Bean
    public SessionService getSessionService() {
        return new SessionService();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
