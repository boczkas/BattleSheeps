package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoShipAtCoordinateException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.util.Map;

public class PlayerBoards {
    private Board board;
    private PlayerShots playerShots;

    public PlayerBoards(Board board, PlayerShots playerShots) {
        this.board = board;
        this.playerShots = playerShots;
    }

    void markHitOnShip(Coordinate coordinate) throws NoShipAtCoordinateException, NoMastAtPositionException {
        board.markHit(coordinate);
    }

    void markMissOnBoard(Coordinate coordinate) {
        playerShots.add(coordinate, ShootResult.MISS);
    }

    void markHitOnShotsBoard(Coordinate shotCoordinate) {
        playerShots.add(shotCoordinate, ShootResult.HIT);
    }

    Board getBoard() {
        return board;
    }

    Map<Coordinate,ShootResult> getPlayerShotsMap() {
        return playerShots.getCoordinateShootResultMap();
    }

    @Override
    public String toString() {
        return "PlayerBoards{" +
                "board=" + board +
                ", playerShots=" + playerShots +
                '}';
    }
}
