package pl.jakubowskiprzemyslaw.REST.utils;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {

    private final SimpMessagingTemplate template;

    public SendMessage(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String playerName, String message) {
        template.convertAndSend("/synchro/timer/" + playerName, message);
    }
}
