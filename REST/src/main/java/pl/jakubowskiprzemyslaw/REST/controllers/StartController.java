package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

  private int amountOfConnectedPlayers = 0;

  @GetMapping(value = "/start", produces = "text/html")
  public String getMainPage() {
    //TODO: 4.07.2018 - This value should be taken from REST API form micro service GameConfigurationRegisterer
    amountOfConnectedPlayers++;
    if(amountOfConnectedPlayers == 1) {
      return "redirect:/gameconfig";
    }
    else if(amountOfConnectedPlayers == 2) {
      return "redirect:/playerconfig";
    }
    else {
      return "noplaces";
    }
  }
}
