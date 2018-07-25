package pl.jakubowskiprzemyslaw.tajgertim.gameconfigurationregister.sendingconfig;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.GameConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class GameConfigurationQueueListenerTest {

    private GameConfigurationQueueListener gameConfigurationQueueListener;
    private ApplicationEventPublisher publisher;
    private GameConfiguration gameConfiguration;

    @BeforeMethod
    public void setUp() {
        gameConfiguration = new GameConfiguration();
        publisher = mock(ApplicationEventPublisher.class);
        LoggerService loggerService = mock(LoggerService.class);
        gameConfigurationQueueListener = new GameConfigurationQueueListener(loggerService, publisher);
    }

    public void correctObjectInGameConfiguration_SendsToQueueGameConfiguration() {
        // given
        gameConfigurationQueueListener.getGameConfigurationFromGameConfigurationQueue(gameConfiguration);

        // when
        GameConfiguredEvent event = new GameConfiguredEvent(this, gameConfiguration);

        // then
        verify(publisher).publishEvent(event);
    }
}
