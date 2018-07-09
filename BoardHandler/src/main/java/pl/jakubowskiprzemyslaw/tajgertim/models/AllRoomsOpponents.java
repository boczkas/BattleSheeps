package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.ArrayList;
import java.util.List;

public class AllRoomsOpponents {
    List<Opponents> allRoomsOpponents;

    public AllRoomsOpponents() {
        this.allRoomsOpponents = new ArrayList<>();
    }

    public void addOpponents(Player player, Player opponent){
        allRoomsOpponents.add(new Opponents(player, opponent));
    }
}
