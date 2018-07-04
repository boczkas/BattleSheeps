package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jakubowskiprzemyslaw.REST.models.Game;

@Controller
public class MainController {

  private int numberOfConnections = 0;

  @GetMapping(value = "/start", produces = "text/html")
  public String getMainPage() {
    //TODO: 4.07.2018 - This value should be taken from REST API form micro service GameConfigurationRegisterer
    numberOfConnections++;
    if(numberOfConnections == 1) {
      return "redirect:/gameconfig";
    }
    else if(numberOfConnections == 2) {
      return "redirect:/playerconfig";
    }
    else {
      return "redirect:/noplaces";
    }
  }
}
