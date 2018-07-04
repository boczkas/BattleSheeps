package pl.jakubowskiprzemyslaw.tajgertim;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RunApp {

  public static void main(String[] args) {
    SpringApplication.run(RunApp.class, args);
  }

}

