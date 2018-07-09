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

    private Map<Player, Board> playersBoards;

    public PlayersBoards() {
        this.playersBoards = new HashMap<>();
    }

    public void addBoardForPlayer(Player player, Board board) {
        playersBoards.put(player, board);
    }

    public FieldState getFieldStatus(Player player, Coordinate coordinate) throws NoMastAtPositionException {
        Board board = playersBoards.get(player);
        return board.getCoordinateStatus(coordinate);
    }

    public void markHitAtBoard(Player player, Coordinate coordinate) throws NoShipAtCoordinateException, NoMastAtPositionException {
        Board board = playersBoards.get(player);
        board.markHit(coordinate);
    }
}
