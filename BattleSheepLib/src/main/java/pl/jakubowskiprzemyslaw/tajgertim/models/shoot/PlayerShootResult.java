package pl.jakubowskiprzemyslaw.tajgertim.models.shoot;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class PlayerShootResult implements QueueObject {
    private static final long serialVersionUID = -1338260362408236054L;
    private Player player;
    private ShootResult shootResult;

    public PlayerShootResult(Player player, ShootResult shootResult) {
        this.player = player;
        this.shootResult = shootResult;
    }

    public Player getPlayer() {
        return player;
    }

    public ShootResult getShootResult() {
        return shootResult;
    }

    @Override
    public String toString() {
        return "PlayerShootResult{" +
                "player=" + player +
                ", shootResult=" + shootResult +
                '}';
    }
}
