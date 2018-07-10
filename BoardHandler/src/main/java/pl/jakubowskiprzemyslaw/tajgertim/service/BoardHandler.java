package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BoardHandler {
    private PlayersBoards playersBoards;
    private AllRoomsOpponents allRoomsOpponents;

    public BoardHandler() {
        this.allRoomsOpponents = new AllRoomsOpponents();
        this.playersBoards = new PlayersBoards();
        addPlayerAndBoardTeeeeeesting();
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

    //TODO: Before first merge:   kill it with fire!
    private void addPlayerAndBoardTeeeeeesting() {
        List<Ship> shipList = new ArrayList<>();
        List<Mast> mastList = new ArrayList<>();
        mastList.add(new Mast(new Coordinate(1, 1)));
        mastList.add(new Mast(new Coordinate(1, 2)));
        mastList.add(new Mast(new Coordinate(1, 3)));
        shipList.add(new Ship(mastList));

        Player staszek = new Player("Staszek", "69");
        playersBoards.addBoardsForPlayer(staszek, new PlayerBoards(new Board(shipList), new PlayerShots()));

        shipList = new ArrayList<>();
        mastList = new ArrayList<>();
        mastList.add(new Mast(new Coordinate(2, 1)));
        mastList.add(new Mast(new Coordinate(2, 2)));
        mastList.add(new Mast(new Coordinate(2, 3)));
        shipList.add(new Ship(mastList));

        Player jozek = new Player("Jozek", "1337");
        playersBoards.addBoardsForPlayer(jozek, new PlayerBoards(new Board(shipList), new PlayerShots()));

        allRoomsOpponents.addOpponents(staszek, jozek);
    }
}
