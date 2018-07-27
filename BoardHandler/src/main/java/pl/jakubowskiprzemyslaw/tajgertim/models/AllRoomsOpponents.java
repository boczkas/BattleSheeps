package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;

import java.util.*;

public class AllRoomsOpponents {
    private Map<Room, Opponents> allRoomsOpponents;
    private String roomName = "roomName";

    public AllRoomsOpponents() {
        this.allRoomsOpponents = new HashMap<>();
    }

    public void addOpponents(Player player, Player opponent) {
        Opponents opponents = new Opponents(player, opponent);
        allRoomsOpponents.put(new Room(roomName), opponents);
    }

    public Player getOpponent(Player player) throws NoSuchPlayerException {

        Optional<Opponents> foundOpponents = allRoomsOpponents.values().stream()
                .filter(opponents -> opponents.contains(player))
                .findFirst();

        if (foundOpponents.isPresent()) {
            Opponents opponents = foundOpponents.get();
            return opponents.getOpponent(player);
        }

        throw new NoSuchPlayerException(player);
    }

    public void addPlayerToRoom(Room room, Player player) {
        if (!allRoomsOpponents.containsKey(room)) {
            Opponents firstOpponent = new Opponents(player);
            allRoomsOpponents.put(room, firstOpponent);
        }
        else {
            allRoomsOpponents.get(room).addOpponent(player);
        }
    }

    public Map<Room, Opponents> getAllRoomsOpponents() {
        return allRoomsOpponents;
    }

    @Override
    public String toString() {
        return "AllRoomsOpponents{" +
                "allRoomsOpponents=" + allRoomsOpponents +
                '}';
    }

    public boolean areOpponentsPresent(String roomName) {
        Opponents opponents = allRoomsOpponents.get(new Room(roomName));

        return opponents.areOpponentsPresent();
    }

    public List<Player> getOpponents(String roomName) {
        Opponents opponents = allRoomsOpponents.get(new Room(roomName));

        return opponents.getOpponents();
    }
}
