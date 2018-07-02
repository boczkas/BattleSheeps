package pl.jakubowskiprzemyslaw.tajgertim.services.builder;

public enum QueueName {
  PLAYER("TurnPlayerQueue"),
  SHOT("TurnShotQueue");

  private String name;

  QueueName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
