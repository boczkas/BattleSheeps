package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Test
public class GameConfigurationServiceTest {


    private QueueService queueService;
    private GameConfigurationQueueListener gameConfigurationQueueListener;
    private ApplicationEventPublisher publisher;
    private GameConfiguredEventListener listener;


    @BeforeMethod
    public void setUp() {
        queueService = mock(QueueService.class);
        LoggerService loggerService = mock(LoggerService.class);
        publisher = mock(ApplicationEventPublisher.class);

        listener = new GameConfiguredEventListener(queueService);
        gameConfigurationQueueListener = new GameConfigurationQueueListener(queueService, loggerService, publisher);
    }

    public void correctObjectInGameConfiguration_SendsToQueueGameConfiguration() {
        GameConfiguration gameConfiguration = new GameConfiguration();
        gameConfigurationQueueListener.getGameConfigurationFromGameConfigurationQueue(gameConfiguration);
        verify(queueService, times(1)).sendObjectToQueue(Queues._3FleetPlacementSizeQueue, gameConfiguration);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void invalidObjectTypeInGameConfiguration_ThrowsClassCastException() {
        Object testObject = new Object();
        gameConfigurationQueueListener.getGameConfigurationFromGameConfigurationQueue((GameConfiguration) testObject);
    }


}
