package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.Arrays;
import java.util.List;

public class Opponents {
    private List<Player> opponents;

    public Opponents(Player player, Player opponent) {
        opponents = Arrays.asList(player, opponent);
    }

    public Player getOpponent(Player player) {
        return opponents.get((opponents.indexOf(player) + 1) % 2);
    }
}
