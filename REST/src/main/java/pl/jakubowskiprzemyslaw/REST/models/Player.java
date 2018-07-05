package pl.jakubowskiprzemyslaw.REST.models;

import java.io.Serializable;

public class Player implements Serializable {

  private static final long serialVersionUID = 5538523552432908894L; //TODO: 4.07.2018 - add more game configuration parameters according to requirements

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
}
