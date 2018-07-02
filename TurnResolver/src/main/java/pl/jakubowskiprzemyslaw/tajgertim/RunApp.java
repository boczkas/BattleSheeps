package pl.jakubowskiprzemyslaw.tajgertim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class RunApp {

  public static void main(String[] args) {
    SpringApplication.run(RunApp.class, args);
  }

}

