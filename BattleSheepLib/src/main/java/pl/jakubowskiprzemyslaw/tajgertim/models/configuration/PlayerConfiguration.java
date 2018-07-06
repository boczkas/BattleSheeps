package pl.jakubowskiprzemyslaw.tajgertim.models.configuration;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class PlayerConfiguration implements QueueObject {

  private final Player player;

  public PlayerConfiguration(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public String toString() {
    return "PlayerConfiguration{" +
            "player=" + player +
            '}';
  }
}
