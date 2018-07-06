package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayingController {

  @GetMapping(value = "/playing", produces = "text/html")
  public String getPlaying() {
    return "playing";
  }
}
