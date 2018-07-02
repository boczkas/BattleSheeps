package pl.jakubowskiprzemyslaw.tajgertim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class OpponentInfoController {

    private final SessionService sessionService;
    private final QueueService queueService;

    @Autowired
    public OpponentInfoController(SessionService sessionService, QueueService queueService) {
        this.sessionService = sessionService;
        this.queueService = queueService;
    }

    @GetMapping(value = "/opponent", produces = "text/plain")
    public @ResponseBody
    String getOpponentName(HttpServletRequest request) throws IOException, TimeoutException {

        Player player = sessionService.getPlayerFromSession(request);

        String playerQueueName = player.obtainQueueName();

        return queueService.getOpponentNameFromQueue(playerQueueName);
    }
}
