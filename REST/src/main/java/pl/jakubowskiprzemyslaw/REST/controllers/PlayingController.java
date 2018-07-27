package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.REST.services.GUIService;
import pl.jakubowskiprzemyslaw.REST.services.PlayerService;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.action.Shot;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlayingController {

    private final QueueService queueService;
    private final PlayerService playerService;
    private final GUIService guiService;
    private String playerName;
    private final SessionService sessionService;

    @Autowired
    PlayingController(QueueService queueService, PlayerService playerService, GUIService guiService, SessionService sessionService) {
        this.queueService = queueService;
        this.playerService = playerService;
        this.guiService = guiService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/playing", produces = "text/html")
    public String getPlaying(HttpServletRequest request) {
        if (!sessionService.isObjectInSession(request, "Player")) {
            return "redirect:/playerconfig";
        }
        playerName = playerService.getPlayerFromRequest(request).getName();
        return "playing";
    }

    @PostMapping(value = "/playing", produces = "text/plain")
    public void makeShot(String guiCoordinates, HttpServletRequest request) {
        Player player = playerService.getPlayerFromRequest(request);
        Coordinate coordinate = guiService.translateGUICoordinatesToCoordinate(guiCoordinates);
        PlayerAction playerAction = new PlayerAction(player, new Shot(coordinate));

        queueService.sendObjectToQueue(Queues._9PlayingStateMachinePlayerActionQueue, playerAction);
    }

    @GetMapping(value = "/getname")
    public @ResponseBody
    String getUserName() {
        return playerName;
    }
}
