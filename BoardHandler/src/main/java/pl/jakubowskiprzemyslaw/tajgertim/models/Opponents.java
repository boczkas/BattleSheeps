package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Opponents {
    private List<Player> opponents;

    Opponents(Player player) {
        opponents = new ArrayList<>();
        opponents.add(player);
    }

    Opponents(Player player, Player opponent) {
        opponents = Arrays.asList(player, opponent);
    }

    public void addOpponent(Player player) {
        opponents.add(player);
    }

    Player getOpponent(Player player) {
        return opponents.get((opponents.indexOf(player) + 1) % 2);
    }

    boolean contains(Player player) {
        return opponents.contains(player);
    }

    boolean isEmpty(){
        return opponents.isEmpty();
    }
}
