package pl.jakubowskiprzemyslaw.tajgertim.models;

import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

public class NoSuchPlayerException extends Throwable {
    private static final long serialVersionUID = 186384893872387081L;

    NoSuchPlayerException(Player player) {
        super("No such a player " + player);
    }
}
