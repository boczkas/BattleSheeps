package pl.jakubowskiprzemyslaw.tajgertim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.services.PlayerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final SessionService sessionService;
    private final QueueService queueService;

    @Autowired
    public PlayerController(PlayerService playerService, SessionService sessionService, QueueService queueService) {
        this.playerService = playerService;
        this.sessionService = sessionService;
        this.queueService = queueService;
    }

    @GetMapping(value = "/player")
    public String getPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "playerconfig";
    }

    @PostMapping(value = "/player")
    public String playerSubmit(@ModelAttribute("player") Player player, HttpServletRequest request) throws IOException, TimeoutException {
        playerService.setPlayerIP(player, request.getRemoteAddr());
        sessionService.savePlayerToSession(player, request.getSession());
        return "result";
    }


}
