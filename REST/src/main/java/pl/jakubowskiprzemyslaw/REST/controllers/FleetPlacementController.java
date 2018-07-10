package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FleetPlacementController{

    private final SessionService sessionService;

    @Autowired
    public FleetPlacementController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/fleetplacement")
    public String getFleetPlacement(HttpServletRequest request) {
        if (!sessionService.isObjectInSession(request, "Player")) {
            return "redirect:/playerconfig";
        }

        return "fleetplacement";
    }
}
