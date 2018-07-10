package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import java.util.List;
import java.util.Map;

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

    public List<Ship> getShipList(){
        return playerBoardView.getShipList();
    }

    public Map<Coordinate, FieldStateView> getFieldView() {
        return opponentBoard.getFieldView();
    }

    public Player getPlayer() {
        return player;
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
