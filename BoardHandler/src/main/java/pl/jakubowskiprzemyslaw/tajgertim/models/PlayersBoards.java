package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.util.HashMap;
import java.util.Map;

public class PlayersBoards {

    private final Map<Player, PlayerBoards> playersBoards;

    public PlayersBoards() {
        this.playersBoards = new HashMap<>();
    }

    public void addBoardsForPlayer(Player player, PlayerBoards playerBoards) {
        playersBoards.put(player, playerBoards);
    }

    public void markHitAtShip(Player player, Coordinate coordinate) throws NoMastAtPositionException {
        PlayerBoards playerPlayerBoards = playersBoards.get(player);
        playerPlayerBoards.markHitOnShip(coordinate);
    }

    public void markHitAtPlayerShotBoard(Player player, Coordinate coordinate) {
        PlayerBoards playerPlayerBoards = playersBoards.get(player);
        playerPlayerBoards.markHitOnShotsBoard(coordinate);
    }

    public void markMissOnBoard(Player player, Coordinate coordinate) {
        PlayerBoards playerBoards = playersBoards.get(player);
        playerBoards.markMissOnShotsBoard(coordinate);
    }

    public FieldState getFieldStatus(Player player, Coordinate coordinate) {
        Board board = playersBoards.get(player).getBoard();
        return board.getCoordinateStatus(coordinate);
    }

    public Board getBoard(Player player) {
        PlayerBoards playerBoards = playersBoards.get(player);
        return playerBoards.getBoard();
    }

    public Map<Coordinate, ShootResult> getPlayerShotsMap(Player player) {
        PlayerBoards playerBoards = playersBoards.get(player);
        return playerBoards.getPlayerShotsMap();
    }

    public void addPlayer(Player player) {
        Fleet fleet = new Fleet();
        PlayerBoards playerBoards = createPlayerBoards(fleet);
        playersBoards.put(player, playerBoards);
    }

    public void addFleetForPlayer(Player player, Fleet fleet) {
        PlayerBoards playerBoards = createPlayerBoards(fleet);
        playersBoards.put(player, playerBoards);
    }

    int getSize() {
        return playersBoards.size();
    }

    private PlayerBoards createPlayerBoards(Fleet fleet) {
        Board board = new Board(fleet);
        PlayerShots playerShots = new PlayerShots();

        return new PlayerBoards(board, playerShots);
    }

    @Override
    public String toString() {
        return "PlayersBoards{" +
                "playersBoards=" + playersBoards +
                '}';
    }
}
