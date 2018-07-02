package pl.jakubowskiprzemyslaw.tajgertim.jobs;

import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersContainer;

@Component
public class PlayerJob {

  public void makeTurn(PlayersContainer container) {
    if (container.getContainerSize() == 2) {
      System.out.println("Loading complete");
      container.clearContainer();
    }
  }
}
