package pl.jakubowskiprzemyslaw.tajgertim.service;

import pl.jakubowskiprzemyslaw.tajgertim.models.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@BattleShipHandler
public class BoardHandler {

    private final PlayersBoards playersBoards;
    private final AllRoomsOpponents allRoomsOpponents;

    public BoardHandler() {
        this.allRoomsOpponents = new AllRoomsOpponents();
        this.playersBoards = new PlayersBoards();
    }

    void markHit(Player player, Coordinate coordinate) throws NoSuchPlayerException, NoMastAtPositionException {
        Player opponent = getOpponent(player);
        playersBoards.markHitAtShip(opponent, coordinate);
        playersBoards.markHitAtPlayerShotBoard(player, coordinate);
    }

    void markMiss(Player player, Coordinate coordinate) {
        playersBoards.markMissOnBoard(player, coordinate); //TODO: 24.07.2018 Mark opponent miss on player board
    }

    FieldState getOpponentFieldStatus(Player player, Coordinate coordinate) throws NoSuchPlayerException {
        Player opponent = getOpponent(player);
        return getPlayerFieldStatus(opponent, coordinate);
    }

    private Player getOpponent(Player player) throws NoSuchPlayerException {
        return allRoomsOpponents.getOpponent(player);
    }

    private FieldState getPlayerFieldStatus(Player player, Coordinate coordinate) {
        return playersBoards.getFieldStatus(player, coordinate);
    }

    Board getPlayerBoard(Player player) {
        return playersBoards.getBoard(player);
    }

    Map<Coordinate, ShootResult> getPlayerShotsMap(Player player) {
        return playersBoards.getPlayerShotsMap(player);
    }

    @Override
    public String toString() {
        return "BoardHandler{" +
                "playersBoards=" + playersBoards +
                ", allRoomsOpponents=" + allRoomsOpponents +
                '}';
    }

    public void addPlayer(Player player) {
        playersBoards.addPlayer(player);
    }

    public void addFleetForPlayer(Player player, Fleet fleet) {
        playersBoards.addFleetForPlayer(player, fleet);
    }

    public void addPlayerToRoom(Room room, Player player) {
        allRoomsOpponents.addPlayerToRoom(room, player);
    }
}
