package pl.jakubowskiprzemyslaw.tajgertim.playerconfigurationregister.sendingplayers;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class PlayerConfigurationQueueListenerTest {

    private ApplicationEventPublisher publisher;
    private PlayerConfiguration playerConfiguration;
    private PlayerConfigurationQueueListener playerConfigurationQueueListener;

    @BeforeMethod
    public void setUp() {
        Player player = new Player("", "");
        playerConfiguration = new PlayerConfiguration(player);
        publisher = mock(ApplicationEventPublisher.class);
        LoggerService loggerService = mock(LoggerService.class);

        playerConfigurationQueueListener = new PlayerConfigurationQueueListener(loggerService, publisher);
    }

    public void listenOnPlayerRegistrationQueue_getPlayerConfiguration_triggerEvent() {
        playerConfigurationQueueListener.getPlayerFromPlayerRegistrationQueue(playerConfiguration);

        PlayerConnectedEvent event = new PlayerConnectedEvent(this, playerConfiguration);
        verify(publisher).publishEvent(event);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnPlayerRegistrationQueue_getObject_throwClassCastException() {
        Object object = new Object();
        playerConfigurationQueueListener.getPlayerFromPlayerRegistrationQueue((PlayerConfiguration) object);
    }
}
