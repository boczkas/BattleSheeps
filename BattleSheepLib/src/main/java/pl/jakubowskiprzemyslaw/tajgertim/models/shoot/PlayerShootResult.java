package pl.jakubowskiprzemyslaw.tajgertim.models.shoot;
import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.Objects;

public class PlayerShootResult implements QueueObject {
    private static final long serialVersionUID = -1338260362408236054L;
    private Player player;
    private ShootResult shootResult;
    private int numberOfOtherMasts;

    public PlayerShootResult(Player player, ShootResult shootResult, int masts) {
        this.player = player;
        this.shootResult = shootResult;
        this.numberOfOtherMasts = masts;
    }

    public Player getPlayer() {
        return player;
    }

    public ShootResult getShootResult() {
        return shootResult;
    }

    public int getNumberOfOtherMasts() {
        return numberOfOtherMasts;
    }

    @Override
    public String toString() {
        return "PlayerShootResult{" +
                "player=" + player +
                ", shootResult=" + shootResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerShootResult result = (PlayerShootResult) o;
        return Objects.equals(player, result.player) &&
                shootResult == result.shootResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, shootResult);
    }
}
