package pl.jakubowskiprzemyslaw.tajgertim.models.round;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

/**
 * Class used for communication between microservices Judge and PlayingStateMachine,
 * to inform PlayingStateMachine which player should
 * play next or if game should end
 */

public class NextRoundStatus implements QueueObject {
    RoundStatus roundStatus;

    public NextRoundStatus() {
        this.roundStatus = RoundStatus.NEXT_PLAYER;
    }

    public NextRoundStatus(RoundStatus roundStatus) {
        this.roundStatus = roundStatus;
    }

    @Override
    public String toString() {
        return "NextRoundStatus{" +
                "roundStatus=" + roundStatus +
                '}';
    }
}
