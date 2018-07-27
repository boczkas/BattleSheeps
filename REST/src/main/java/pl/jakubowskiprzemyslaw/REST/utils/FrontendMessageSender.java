package pl.jakubowskiprzemyslaw.REST.utils;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Component
public class FrontendMessageSender {

    private final SimpMessagingTemplate template;

    public FrontendMessageSender(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String playerName, String message) {
        template.convertAndSend("/synchro/timer/" + playerName, message);
    }

    public void sendBoards(String playerName, ObjectNode message) {
        template.convertAndSend("/synchro/boards/" + playerName, message);
    }
}
