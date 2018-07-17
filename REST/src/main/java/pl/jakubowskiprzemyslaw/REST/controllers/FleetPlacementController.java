package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakubowskiprzemyslaw.REST.services.FleetGeneratorService;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.FleetPlacement;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FleetPlacementController{

    private final SessionService sessionService;
    private final QueueService queueService;
    private final FleetGeneratorService fleetGeneratorService;

    @Autowired
    public FleetPlacementController(SessionService sessionService, QueueService queueService, FleetGeneratorService fleetGeneratorService) {
        this.sessionService = sessionService;
        this.queueService = queueService;
        this.fleetGeneratorService = fleetGeneratorService;
    }

    @GetMapping(value = "/fleetplacement", produces = "text/html")
        public String getFleetPlacement(HttpServletRequest request) {
        if (!sessionService.isObjectInSession(request, "Player")) {
            return "redirect:/playerconfig";
        }
        return "fleetplacement";
    }

    @PostMapping(value = "/fleetplacement", produces = "text/html")
    public String placeFleetAndPlay(@ModelAttribute("fleet") Fleet fleet, HttpServletRequest request) {

        Player player = (Player) request.getSession().getAttribute("Player");
        fleet = fleetGeneratorService.generateRandomFleet();

        queueService.sendObjectToQueue("FleetPlacementQueue", new FleetPlacement(player, fleet));

        return "playing";
    }
}
