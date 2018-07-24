package pl.jakubowskiprzemyslaw.tajgertim.models.shoot;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class PlayerShootCoordinate implements QueueObject {
    private static final long serialVersionUID = 6941519339539583564L;
    private Player player;
    private Coordinate coordinate;

    public PlayerShootCoordinate() {
        this.player = new Player("", "");
        this.coordinate = new Coordinate(0,0);
    }

    public PlayerShootCoordinate(Player player, Coordinate coordinate) {
        this.player = player;
        this.coordinate = coordinate;
    }

    public Player getPlayer() {
        return player;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "PlayerShootCoordinate{" +
                "player=" + player +
                ", coordinate=" + coordinate +
                '}';
    }
}
