package pl.jakubowskiprzemyslaw.REST.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Component
public class FrontendMessageSenderService {

    private final SimpMessagingTemplate template;

    public FrontendMessageSenderService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String playerName, String message) {
        template.convertAndSend("/synchro/playerturn/" + playerName, message);
    }

    public void sendBoards(String playerName, ObjectNode message) {
        template.convertAndSend("/synchro/boards/" + playerName, message);
    }
}
