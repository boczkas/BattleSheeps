package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;

import java.io.Serializable;

public class PlayerBoardView implements Serializable {
    private static final long serialVersionUID = 8516776033343687527L;
    private Board board;

    public PlayerBoardView(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "PlayerBoardView{" +
                "board=" + board +
                '}';
    }
}
