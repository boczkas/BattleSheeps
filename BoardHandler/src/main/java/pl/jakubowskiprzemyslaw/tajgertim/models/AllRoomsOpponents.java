package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllRoomsOpponents {
    private List<Opponents> allRoomsOpponents;

    public AllRoomsOpponents() {
        this.allRoomsOpponents = new ArrayList<>();
    }

    public void addOpponents(Player player, Player opponent) {
        Opponents opponents = new Opponents(player, opponent);
        allRoomsOpponents.add(opponents);
    }

    @Override
    public String toString() {
        return "AllRoomsOpponents{" +
                "allRoomsOpponents=" + allRoomsOpponents +
                '}';
    }

    public Player getOpponent(Player player) throws NoSuchPlayerException {

        Optional<Opponents> foundOpponents = allRoomsOpponents.stream()
                .filter(opponents -> opponents.contains(player))
                .findFirst();

        if(foundOpponents.isPresent()){
            Opponents opponents = foundOpponents.get();
            return opponents.getOpponent(player);
        }

        throw new NoSuchPlayerException(player);
    }
}
