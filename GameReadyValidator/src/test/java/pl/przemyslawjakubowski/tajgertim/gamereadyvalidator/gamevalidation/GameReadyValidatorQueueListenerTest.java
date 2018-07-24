package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.springframework.context.ApplicationEventPublisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.Confirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.FleetPlacementConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.GameConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.PlayerConfigurationConfirmation;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class GameReadyValidatorQueueListenerTest {

    private ApplicationEventPublisher publisher;
    private GameReadyValidatorQueueListener gameReadyValidatorQueueListener;

    @BeforeMethod
    public void setUp() {
        publisher = mock(ApplicationEventPublisher.class);
        LoggerService loggerService = mock(LoggerService.class);

        gameReadyValidatorQueueListener = new GameReadyValidatorQueueListener(loggerService, publisher);
    }

    public void listenOnGameReadyValidationQueue_getGameConfigurationConfirmation_triggerEvent() {
        Confirmation gameConfigurationConfirmation = new GameConfigurationConfirmation();
        gameReadyValidatorQueueListener.getConfirmationsFromGameReadyValidatorQueue(gameConfigurationConfirmation);

        ConfirmationReceivedEvent confirmationReceivedEvent = new ConfirmationReceivedEvent(this, gameConfigurationConfirmation);
        verify(publisher).publishEvent(confirmationReceivedEvent);
    }

    public void listenOnGameReadyValidationQueue_getPlayerConfigurationConfirmation_triggerEvent() {
        Confirmation playerConfigurationConfirmation = new PlayerConfigurationConfirmation();
        gameReadyValidatorQueueListener.getConfirmationsFromGameReadyValidatorQueue(playerConfigurationConfirmation);

        ConfirmationReceivedEvent confirmationReceivedEvent = new ConfirmationReceivedEvent(this, playerConfigurationConfirmation);
        verify(publisher).publishEvent(confirmationReceivedEvent);
    }

    public void listenOnGameReadyValidationQueue_getFleetPlacementConfirmation_triggerEvent() {
        Confirmation fleetPlacementConfirmation = new FleetPlacementConfirmation();
        gameReadyValidatorQueueListener.getConfirmationsFromGameReadyValidatorQueue(fleetPlacementConfirmation);

        ConfirmationReceivedEvent confirmationReceivedEvent = new ConfirmationReceivedEvent(this, fleetPlacementConfirmation);
        verify(publisher).publishEvent(confirmationReceivedEvent);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void listenOnGameReadyValidationQueue_getObject_throwClassCastException() {
        Object object = new Object();
        gameReadyValidatorQueueListener.getConfirmationsFromGameReadyValidatorQueue((Confirmation) object);
    }


}
