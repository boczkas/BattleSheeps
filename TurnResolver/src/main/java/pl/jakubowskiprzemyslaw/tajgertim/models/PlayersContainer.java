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

  public Player getPlayer(String playerName) {
    return players.get(playerName);
  }

  public int getNumberOfPlayers() {
    return players.size();
  }
}
