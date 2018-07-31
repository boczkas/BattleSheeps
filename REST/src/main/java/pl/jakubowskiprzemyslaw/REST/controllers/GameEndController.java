package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameEndController {

    @GetMapping(value = "gameend")
    public String returnGameEnd() {
        return "gameend";
    }
}
