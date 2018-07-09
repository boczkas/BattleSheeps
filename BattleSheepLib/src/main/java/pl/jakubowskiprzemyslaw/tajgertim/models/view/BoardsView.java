package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class BoardsView implements QueueObject {
    private static final long serialVersionUID = -3712923010769553757L;
    private PlayerBoardView playerBoardView;
    private OpponentBoardView opponentBoard;
    private Player player;

    public BoardsView(Player player, PlayerBoardView playerBoardView, OpponentBoardView opponentBoard) {
        this.playerBoardView = playerBoardView;
        this.opponentBoard = opponentBoard;
        this.player = player;
    }

    public PlayerBoardView getPlayerBoardView() {
        return playerBoardView;
    }

    public OpponentBoardView getOpponentBoard() {
        return opponentBoard;
    }

    @Override
    public String toString() {
        return "BoardsView{" +
                "playerBoardView=" + playerBoardView +
                ", opponentBoard=" + opponentBoard +
                ", player=" + player +
                '}';
    }
}
