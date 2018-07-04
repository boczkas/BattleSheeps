package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


  @GetMapping(value = "/", produces = "text/html")
  public String getMainPage() {
    return "redirect:/gameconfig";
  }
}
