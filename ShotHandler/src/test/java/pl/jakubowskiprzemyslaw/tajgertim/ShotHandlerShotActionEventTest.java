package pl.jakubowskiprzemyslaw.tajgertim;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.event.FieldStatusEvent;
import pl.jakubowskiprzemyslaw.tajgertim.event.ShotActionEvent;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldStatus;
import pl.jakubowskiprzemyslaw.tajgertim.models.playeraction.PlayerAction;
import pl.jakubowskiprzemyslaw.tajgertim.service.ShotHandlerQueueListener;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
public class ShotHandlerShotActionEventTest {

    private ShotActionEvent event;
    private PlayerAction action;

    @BeforeMethod
    public void setUp() {
        ShotHandlerQueueListener queueListener = mock(ShotHandlerQueueListener.class);
        action = mock(PlayerAction.class);
        event = new ShotActionEvent(queueListener, action);
    }

    public void equalsTheSame_ReturnsTrue() {
        assertEquals(event, event);
    }

    public void equalsNull_ReturnsFalse() {
        assertNotEquals(event, null);
    }

    public void equalsAnotherObject_ReturnsTrue_IfAnotherObjectIsSimilar() {
        ShotActionEvent configuredEvent = new ShotActionEvent(mock(ShotHandlerQueueListener.class), action);
        assertEquals(event, configuredEvent);
    }
}
