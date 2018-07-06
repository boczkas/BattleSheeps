package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import java.io.Serializable;

public final class Player implements Serializable {

    private static final long serialVersionUID = 7538406378948395353L;
    private final String name;
    private String IP;

    public Player(String name, String IP) {
        this.name = name;
        this.IP = IP;
    }

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

    public String getIP() {
        return IP;
    }

  public void setIP(String IP) {
    this.IP = IP;
  }
}
