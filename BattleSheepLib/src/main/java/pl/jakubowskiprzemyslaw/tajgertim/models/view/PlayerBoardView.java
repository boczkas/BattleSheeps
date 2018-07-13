package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;

import java.io.Serializable;
import java.util.List;

public class PlayerBoardView implements Serializable {
    private static final long serialVersionUID = 8516776033343687527L;
    private Board board;

    public PlayerBoardView(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    List<Ship> getShipList() {
        return board.getShipList();
    }

    @Override
    public String toString() {
        return "PlayerBoardView{" +
                "board=" + board +
                '}';
    }
}
