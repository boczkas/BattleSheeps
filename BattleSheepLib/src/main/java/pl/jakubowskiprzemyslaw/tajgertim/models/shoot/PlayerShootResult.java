package pl.jakubowskiprzemyslaw.tajgertim.models.shoot;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class PlayerShootResult implements QueueObject {
    Player player;
    ShootResult shootResult;

    public PlayerShootResult() {
        this.player = new Player("", "");
        this.shootResult = ShootResult.MISS;
    }

    public PlayerShootResult(Player player, ShootResult shootResult) {
        this.player = player;
        this.shootResult = shootResult;
    }

    @Override
    public String toString() {
        return "PlayerShootResult{" +
                "player=" + player +
                ", shootResult=" + shootResult +
                '}';
    }
}
