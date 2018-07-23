package pl.jakubowskiprzemyslaw.tajgertim.models.view;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

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

    public List<Ship> getShipList() {
        return playerBoardView.getShipList();
    }

    public Map<Coordinate, ShootResult> getShootingMap() {
        return opponentBoard.getShootingMap();
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
