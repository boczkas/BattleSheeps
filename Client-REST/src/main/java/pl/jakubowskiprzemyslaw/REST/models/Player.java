package pl.jakubowskiprzemyslaw.REST.models;

import java.io.Serializable;

public class Player implements Serializable { //TODO: 4.07.2018 - add more game configuration parameters according to requirements

  private String playerName;
  private String playerIP;

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerIP() {
    return playerIP;
  }

  public void setPlayerIP(String playerIP) {
    this.playerIP = playerIP;
  }

  @Override
  public String toString() {
    return "Player{" +
            "playerName='" + playerName + '\'' +
            ", playerIP='" + playerIP + '\'' +
            '}';
  }
}
