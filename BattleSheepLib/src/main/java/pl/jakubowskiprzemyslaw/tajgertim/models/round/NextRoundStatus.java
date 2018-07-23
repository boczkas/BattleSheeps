package pl.jakubowskiprzemyslaw.tajgertim.models.round;

import pl.jakubowskiprzemyslaw.tajgertim.models.QueueObject;

import java.util.Objects;

/**
 * Class used for communication between microservices Judge and PlayingStateMachine,
 * to inform PlayingStateMachine which player should
 * play next or if game should end
 */

public class NextRoundStatus implements QueueObject {
    private static final long serialVersionUID = -2030492077082436469L;
    private RoundStatus roundStatus;

    public NextRoundStatus(RoundStatus roundStatus) {
        this.roundStatus = roundStatus;
    }

    @Override
    public String toString() {
        return "NextRoundStatus{" +
                "roundStatus=" + roundStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NextRoundStatus that = (NextRoundStatus) o;
        return roundStatus == that.roundStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundStatus);
    }
}
