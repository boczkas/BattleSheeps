package pl.jakubowskiprzemyslaw.tajgertim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;
import pl.jakubowskiprzemyslaw.tajgertim.queues.PlayerRegistrationQueueConnector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class PlayerController {

    private final PlayerRegistrationQueueConnector queueConnector;

    @Autowired
    public PlayerController(PlayerRegistrationQueueConnector queueConnector) {
        this.queueConnector = queueConnector;
    }

    @GetMapping(value = "/player")
    public String getPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "player";
    }

    @PostMapping(value = "/player")
    public String playerSubmit(@ModelAttribute("player") Player player, HttpServletRequest request) throws IOException, TimeoutException {
        setPlayerIP(player, request.getRemoteAddr());

        savePlayerToSession(player, request.getSession());

        sendPlayerToQueue(player);
        return "result";
    }

    private void sendPlayerToQueue(Player player) throws IOException, TimeoutException {
        queueConnector.connectToQueue();
        queueConnector.sendPlayerToQueue(player);
//        playerQueueConnector.closeConnection();   //TODO: 13.07.2018 - connection is not closed, because rzuca bledami
    }

    private void savePlayerToSession(Player player, HttpSession session) {
        session.setAttribute("player", player);
    }

    private void setPlayerIP(Player player, String IP) {
        player.setIP(IP);
    }
}
