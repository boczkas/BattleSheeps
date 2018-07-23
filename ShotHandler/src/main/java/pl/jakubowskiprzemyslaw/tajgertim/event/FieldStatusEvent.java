package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.context.ApplicationEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;

import java.util.Objects;

public class FieldStatusEvent extends ApplicationEvent {
    private static final long serialVersionUID = 4884461803892942164L;
    private FieldStatus fieldStatus;

    public FieldStatusEvent(Object source, FieldStatus status) {
        super(source);
        this.fieldStatus = status;
    }

    FieldStatus getFieldStatus() {
        return fieldStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldStatusEvent that = (FieldStatusEvent) o;
        return Objects.equals(fieldStatus, that.fieldStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldStatus);
    }
}
