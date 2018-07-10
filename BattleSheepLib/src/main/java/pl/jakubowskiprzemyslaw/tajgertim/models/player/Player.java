package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(IP, player.IP);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, IP);
    }

  public void setIP(String IP) {
    this.IP = IP;
  }
}
