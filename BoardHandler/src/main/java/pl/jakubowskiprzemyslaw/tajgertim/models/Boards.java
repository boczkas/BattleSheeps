package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoShipAtCoordinateException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

public class Boards {
    private Board board;
    private PlayerShots playerShots;

    public Boards(Board board, PlayerShots playerShots) {
        this.board = board;
        this.playerShots = playerShots;
    }

    public Board getBoard() {
        return board;
    }

    public void markHitOnShip(Coordinate coordinate) throws NoShipAtCoordinateException, NoMastAtPositionException {
        board.markHit(coordinate);
    }

    public void markMissOnBoard(Coordinate coordinate) {
        playerShots.add(coordinate, ShootResult.MISS);
    }

    @Override
    public String toString() {
        return "Boards{" +
                "board=" + board +
                ", playerShots=" + playerShots +
                '}';
    }

    public void markHitOnBoard(Coordinate shotCoordinate) {
        playerShots.add(shotCoordinate, ShootResult.HIT);
    }
}
