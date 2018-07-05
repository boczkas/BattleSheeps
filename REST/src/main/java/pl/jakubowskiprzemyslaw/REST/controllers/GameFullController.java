package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameFullController {

  @GetMapping
  public String gameFull() {
    return "noplaces";
  }
}
