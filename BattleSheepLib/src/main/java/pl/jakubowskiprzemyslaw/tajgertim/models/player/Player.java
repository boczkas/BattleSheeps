package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import java.io.Serializable;

public class Player implements Serializable {
  private String name;
  private String IP;

  @Override
  public String toString() {
    return "Player{" +
            "name='" + name + '\'' +
            ", IP='" + IP + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIP() {
    return IP;
  }

  public void setIP(String IP) {
    this.IP = IP;
  }

}
