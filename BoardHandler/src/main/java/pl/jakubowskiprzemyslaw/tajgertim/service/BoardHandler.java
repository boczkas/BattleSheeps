package pl.jakubowskiprzemyslaw.tajgertim.service;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Mast;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardHandler {
    private Map<Player, Board> playersBoards;

    public BoardHandler() {
        this.playersBoards = new HashMap<>();
        addPlayerAndBoardTeeeeeesting();
    }

    private void addPlayerAndBoardTeeeeeesting() {
        List<Ship> shipList = new ArrayList<>();
        List<Mast> mastList = new ArrayList<>();
        mastList.add(new Mast(new Coordinate(1, 1)));
        mastList.add(new Mast(new Coordinate(1, 2)));
        mastList.add(new Mast(new Coordinate(1, 3)));
        shipList.add(new Ship(mastList));

        playersBoards.put(new Player("Staszek", "69"), new Board(shipList));

        shipList = new ArrayList<>();
        mastList = new ArrayList<>();
        mastList.add(new Mast(new Coordinate(2, 1)));
        mastList.add(new Mast(new Coordinate(2, 2)));
        mastList.add(new Mast(new Coordinate(2, 3)));
        shipList.add(new Ship(mastList));

        playersBoards.put(new Player("Jozek", "1337"), new Board(shipList));
    }
}
