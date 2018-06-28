package pl.jakubowskiprzemyslaw.tajgertim;

public class Player {
    String name;
    String IP;

    public Player() {
    }

    public Player(String name, String IP) {
        this.name = name;
        this.IP = IP;
    }

    @Override
    public String toString() {
        return name + " " + IP;
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
