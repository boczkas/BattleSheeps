package pl.jakubowskiprzemyslaw.tajgertim.models.configuration;

import java.io.Serializable;

public final class GameConfiguration implements Serializable {

  private static final long serialVersionUID = -6981739379739109856L; //TODO: 4.07.2018 - add more game configuration parameters according to requirements

  private String gameName;

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  @Override
  public String toString() {
    return "GameConfiguration{" +
            "gameName='" + gameName + '\'' +
            '}';
  }
}
