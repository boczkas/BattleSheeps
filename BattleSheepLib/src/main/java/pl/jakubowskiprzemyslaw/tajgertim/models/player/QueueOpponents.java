package pl.jakubowskiprzemyslaw.tajgertim.models.player;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

import java.util.ArrayList;
import java.util.List;

public class QueueOpponents implements QueueObject {
    private static final long serialVersionUID = -1441279308004351636L;
    private List<Player> opponents = new ArrayList<>();

    public QueueOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }
}
