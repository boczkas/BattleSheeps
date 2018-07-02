package pl.jakubowskiprzemyslaw.tajgertim.models;

public class Player {
    private String name;
    private String IP;

    public Player() {
    }

    public Player(String name, String IP) {
        this.name = name;
        this.IP = IP;
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

    public String obtainQueueName() {
        return  "PlayerOpponentQueue" + name + IP;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", IP='" + IP + '\'' +
                '}';
    }

}
