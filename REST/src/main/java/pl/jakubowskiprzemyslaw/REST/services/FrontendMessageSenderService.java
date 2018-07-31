package pl.jakubowskiprzemyslaw.REST.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Component
public class FrontendMessageSenderService {

    private final SimpMessagingTemplate template;

    public FrontendMessageSenderService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendPlayingPlayer(String playerID) {
        template.convertAndSend("/synchro/playerturn/" + playerID, playerID);
    }

    public void sendBoards(String playerName, ObjectNode message) {
        template.convertAndSend("/synchro/boards/" + playerName, message);

    }

    public void endGame(String playerID, String opponentName) {
        template.convertAndSend("/synchro/winner/" + playerID, "WINNER!");
        template.convertAndSend("/synchro/winner/" + opponentName, "LOSER!");
    }
}
