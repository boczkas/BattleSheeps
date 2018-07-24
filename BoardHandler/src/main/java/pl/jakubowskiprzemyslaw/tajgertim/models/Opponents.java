package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.Arrays;
import java.util.List;

class Opponents {
    private List<Player> opponents;

    Opponents(Player player, Player opponent) {
        opponents = Arrays.asList(player, opponent);
    }

    Player getOpponent(Player player) {
        return opponents.get((opponents.indexOf(player) + 1) % 2);
    }

    boolean contains(Player player) {
        return opponents.contains(player);
    }
}
