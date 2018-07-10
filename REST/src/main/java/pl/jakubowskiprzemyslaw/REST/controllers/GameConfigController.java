package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class GameConfigController {

    private final SessionService sessionService;
    private final QueueService queueService;

    @Autowired
    public GameConfigController(SessionService sessionService, QueueService queueService) {
        this.sessionService = sessionService;
        this.queueService = queueService;
    }

    @GetMapping(value = "/gameconfig", produces = "text/html")
    public String getGameConfig(Model model) {
        model.addAttribute("configuration", new GameConfiguration());
        return "gameconfig";
    }

    @PostMapping(value = "/gameconfig", produces = "text/html")
    public String sendGameConfig(@Valid @ModelAttribute("configuration") GameConfiguration configuration, HttpServletRequest request) {
        sessionService.addObjectToSessionRequest(request, configuration);
        queueService.sendObjectToQueue(Queues._2GameConfigurationRegistrationQueue, configuration);
        return "redirect:/playerconfig";
    }
}