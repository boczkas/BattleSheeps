package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.util.Map;

@Service
public class BoardHandler {

    private PlayersBoards playersBoards;
    private AllRoomsOpponents allRoomsOpponents;

    public BoardHandler() {
        this.allRoomsOpponents = new AllRoomsOpponents();
        this.playersBoards = new PlayersBoards();
    }

    void markHit(Player player, Coordinate coordinate) throws NoSuchPlayerException, NoShipAtCoordinateException, NoMastAtPositionException {
        Player opponent = getOpponent(player);
        playersBoards.markHitAtShip(opponent, coordinate);
        playersBoards.markHitAtPlayerShotBoard(player, coordinate);
    }

    void markMiss(Player player, Coordinate coordinate) {
        playersBoards.markMissOnBoard(player, coordinate); //TODO: 24.07.2018 Mark opponent miss on player board
    }

    FieldState getOpponentFieldStatus(Player player, Coordinate coordinate) throws NoSuchPlayerException, NoMastAtPositionException {
        Player playerOpponent = getOpponent(player);
        return getPlayerFieldStatus(playerOpponent, coordinate);
    }

    private Player getOpponent(Player player) throws NoSuchPlayerException {
        return allRoomsOpponents.getOpponent(player);
    }

    private FieldState getPlayerFieldStatus(Player player, Coordinate coordinate) throws NoMastAtPositionException {
        return playersBoards.getFieldStatus(player, coordinate);
    }

    Board getPlayerBoard(Player player) {
        return playersBoards.getBoard(player);
    }

    Map<Coordinate,ShootResult> getPlayerShotsMap(Player player) {
        return playersBoards.getPlayerShotsMap(player);
    }

    @Override
    public String toString() {
        return "BoardHandler{" +
                "playersBoards=" + playersBoards +
                ", allRoomsOpponents=" + allRoomsOpponents +
                '}';
    }
}
