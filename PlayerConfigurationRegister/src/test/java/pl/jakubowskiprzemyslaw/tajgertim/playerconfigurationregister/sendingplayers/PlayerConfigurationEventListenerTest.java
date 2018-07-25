package pl.jakubowskiprzemyslaw.tajgertim.playerconfigurationregister.sendingplayers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.PlayerConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class PlayerConfigurationEventListenerTest {

    private PlayerConfiguration playerConfiguration;
    private PlayerConnectedEventListener eventListener;
    private QueueService queueService;

    @BeforeMethod
    public void setUp() {
        playerConfiguration = mock(PlayerConfiguration.class);
        queueService = mock(QueueService.class);
        eventListener = new PlayerConnectedEventListener(queueService);
    }

    public void onePlayerConfigObject_IsSending_ToBHPlayerQueue() {
        PlayerConnectedEvent event = new PlayerConnectedEvent(this, playerConfiguration);
        eventListener.onApplicationEvent(event);

        verify(queueService).sendObjectToQueue(Queues._6BoardHandlerPlayerQueue, playerConfiguration);
    }

    public void twoPlayerConfigObjects_Send_PlayerConfirmation() {
        PlayerConnectedEvent event = new PlayerConnectedEvent(this, playerConfiguration);
        eventListener.onApplicationEvent(event);
        eventListener.onApplicationEvent(event);

        Confirmation playersRegistered = new PlayerConfigurationConfirmation();
        verify(queueService).sendObjectToQueue(Queues._5GameReadyValidationQueue, playersRegistered);
    }
}
