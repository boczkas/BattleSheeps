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

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlayingController {

    private final QueueService queueService;
    private final PlayerService playerService;
    private final GUIService guiService;
    private String playerName;
    private final PlayerNameHandler sessionIDHandler;

    @Autowired
    PlayingController(QueueService queueService, PlayerService playerService, GUIService guiService, PlayerNameHandler sessionIDHandler) {
        this.queueService = queueService;
        this.playerService = playerService;
        this.guiService = guiService;
        this.sessionIDHandler = sessionIDHandler;
    }

    @GetMapping(value = "/playing", produces = "text/html")
    public String getPlaying(HttpServletRequest request) {
        playerName = ((Player) request.getSession().getAttribute("Player")).getName();
        sessionIDHandler.setName(playerName);
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
