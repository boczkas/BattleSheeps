package pl.jakubowskiprzemyslaw.REST.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GameConfiguration implements Serializable {

  private String gameName;
  private String ip;

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  @Override
  public String toString() {
    return "GameConfiguration{" +
            "gameName='" + gameName + '\'' +
            ", ip='" + ip + '\'' +
            '}';
  }
}
