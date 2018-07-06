package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayingController {

  @GetMapping(value = "/playing", produces = "text/html")
  public String getPlaying() {
    return "playing";
  }

  @PostMapping(value = "/playing", produces = "text/plain")
  public String makeShoot(String coordinates) {
    System.out.println(coordinates);
    return "playing";
  }
}
