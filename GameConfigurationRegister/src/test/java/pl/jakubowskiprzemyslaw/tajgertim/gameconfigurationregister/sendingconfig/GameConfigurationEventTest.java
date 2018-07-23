package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
public class GameConfigurationEventTest {

    private GameConfiguredEvent event;
    private GameConfiguration configuration;

    @BeforeMethod
    public void setUp() {
        GameConfigurationQueueListener queueListener = mock(GameConfigurationQueueListener.class);
        configuration = new GameConfiguration();
        event = new GameConfiguredEvent(queueListener, configuration);
    }

    public void equalsTheSame_ReturnsTrue() {
        assertEquals(event, event);
    }

    public void equalsNull_ReturnsFalse() {
        assertNotEquals(event, null);
    }

    public void equalsAnotherObject_ReturnsTrue_IfAnotherObjectIsSimilar() {
        GameConfiguredEvent configuredEvent = new GameConfiguredEvent(mock(GameConfigurationQueueListener.class), configuration);
        assertEquals(event, configuredEvent);
    }
}
