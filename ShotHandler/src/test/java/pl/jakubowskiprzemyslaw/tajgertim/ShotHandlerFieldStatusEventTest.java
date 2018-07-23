package pl.jakubowskiprzemyslaw.tajgertim;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.FieldStatusEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.service.ShotHandlerQueueListener;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
public class ShotHandlerFieldStatusEventTest {

    private FieldStatusEvent event;
    private FieldStatus status;

    @BeforeMethod
    public void setUp() {
        ShotHandlerQueueListener queueListener = mock(ShotHandlerQueueListener.class);
        status = mock(FieldStatus.class);
        event = new FieldStatusEvent(queueListener, status);
    }

    public void equalsTheSame_ReturnsTrue() {
        assertEquals(event, event);
    }

    public void equalsNull_ReturnsFalse() {
        assertNotEquals(event, null);
    }

    public void equalsAnotherObject_ReturnsTrue_IfAnotherObjectIsSimilar() {
        FieldStatusEvent configuredEvent = new FieldStatusEvent(mock(ShotHandlerQueueListener.class), status);
        assertEquals(event, configuredEvent);
    }

}
