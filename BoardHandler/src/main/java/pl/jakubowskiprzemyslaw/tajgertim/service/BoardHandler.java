package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.AllRoomsOpponents;
import pl.jakubowskiprzemyslaw.tajgertim.models.NoSuchPlayerException;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersBoards;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.*;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
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

    //TODO: Before first merge:   kill it with fire!
    private void addPlayerAndBoardTeeeeeesting() {
        List<Ship> shipList = new ArrayList<>();
        List<Mast> mastList = new ArrayList<>();
        mastList.add(new Mast(new Coordinate(1, 1)));
        mastList.add(new Mast(new Coordinate(1, 2)));
        mastList.add(new Mast(new Coordinate(1, 3)));
        shipList.add(new Ship(mastList));

        Player staszek = new Player("Staszek", "69");
        playersBoards.addBoardForPlayer(staszek, new Board(shipList));

        shipList = new ArrayList<>();
        mastList = new ArrayList<>();
        mastList.add(new Mast(new Coordinate(2, 1)));
        mastList.add(new Mast(new Coordinate(2, 2)));
        mastList.add(new Mast(new Coordinate(2, 3)));
        shipList.add(new Ship(mastList));

        Player jozek = new Player("Jozek", "1337");
        playersBoards.addBoardForPlayer(jozek, new Board(shipList));

        allRoomsOpponents.addOpponents(staszek, jozek);
    }

    @Override
    public String toString() {
        return "BoardHandler{" +
                "playersBoards=" + playersBoards +
                ", allRoomsOpponents=" + allRoomsOpponents +
                '}';
    }

    private Player getOpponent(Player player) throws NoSuchPlayerException {
        return allRoomsOpponents.getOpponent(player);
    }

    FieldState getPlayerFieldStatus(Player player, Coordinate coordinate) throws NoMastAtPositionException {
        return playersBoards.getFieldStatus(player, coordinate);
    }

    FieldState getOpponentFieldStatus(Player player, Coordinate coordinate) throws NoSuchPlayerException, NoMastAtPositionException {
        Player playerOpponent = getOpponent(player);
        return getPlayerFieldStatus(playerOpponent, coordinate);
    }

    void markHitAtOpponentBoard(Player player, Coordinate shotCoordinate) throws NoSuchPlayerException, NoShipAtCoordinateException, NoMastAtPositionException {
        Player opponent = getOpponent(player);
        playersBoards.markHitAtBoard(opponent, shotCoordinate);
    }
}
