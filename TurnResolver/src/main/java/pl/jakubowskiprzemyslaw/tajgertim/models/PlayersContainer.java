package pl.jakubowskiprzemyslaw.tajgertim.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayersContainer {

  private Map<String, Player> players = new HashMap<>();

  public void addPlayer(Player player) {
    String key = player.getName();
    players.put(key, player);
  }

  public int getContainerSize() {
    return players.size();
  }

  public void clearContainer() {
    players.clear();
  }
}
