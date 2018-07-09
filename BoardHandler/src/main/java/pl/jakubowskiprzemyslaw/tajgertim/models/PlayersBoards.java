package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoShipAtCoordinateException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayersBoards {

    private Map<Player, Boards> playersBoards;

    public PlayersBoards() {
        this.playersBoards = new HashMap<>();
    }

    public void addBoardsForPlayer(Player player, Boards boards) {
        playersBoards.put(player, boards);
    }

    public FieldState getFieldStatus(Player player, Coordinate coordinate) throws NoMastAtPositionException {
        Board board = playersBoards.get(player).getBoard();
        return board.getCoordinateStatus(coordinate);
    }

    public void markHitAtShip(Player player, Coordinate coordinate) throws NoShipAtCoordinateException, NoMastAtPositionException {
        Boards playerBoards = playersBoards.get(player);
        playerBoards.markHitOnShip(coordinate);
    }

    public void markHitAtBoard(Player player, Coordinate shotCoordinate) {
        Boards playerBoards = playersBoards.get(player);
        playerBoards.markHitOnBoard(shotCoordinate);
    }

    public void markMissAtBoard(Player player, Coordinate shotCoordinate) {
        Boards playerBoards = playersBoards.get(player);
        playerBoards.markMissOnBoard(shotCoordinate);
    }

    @Override
    public String toString() {
        return "PlayersBoards{" +
                "playersBoards=" + playersBoards +
                '}';
    }
}
