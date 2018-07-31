package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

import java.util.Objects;

public class PlayingPlayer implements QueueObject {

    private static final long serialVersionUID = 8288165593921322085L;

    private final Player player;
    private boolean winnerStatus = false;
    private String opponentName;

    public PlayingPlayer(Player player, String opponent) {
        this.player = player;
        this.opponentName = opponent;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public void setWinner() {
        winnerStatus = true;
    }

    public boolean isWinner() {
        return winnerStatus;
    }

    public String getOpponentName() {
        return opponentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingPlayer that = (PlayingPlayer) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {

        return Objects.hash(player);
    }

    @Override
    public String toString() {
        return "PlayingPlayer{" +
                "player=" + player +
                '}';
    }
}
