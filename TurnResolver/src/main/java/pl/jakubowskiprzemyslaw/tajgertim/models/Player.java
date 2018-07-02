package pl.jakubowskiprzemyslaw.tajgertim.models;

import java.io.Serializable;

public class Player implements Serializable {
  String name;
  String IP;
  String queueName;

  @Override
  public String toString() {
    return "Player{" +
            "name='" + name + '\'' +
            ", IP='" + IP + '\'' +
            ", queueName='" + queueName + '\'' +
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

  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }
}
