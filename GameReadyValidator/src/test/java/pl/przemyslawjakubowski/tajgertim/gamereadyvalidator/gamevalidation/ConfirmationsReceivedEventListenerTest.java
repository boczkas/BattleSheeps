package pl.przemyslawjakubowski.tajgertim.gamereadyvalidator.gamevalidation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.confirmation.*;
import pl.jakubowskiprzemyslaw.tajgertim.queues.Queues;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.*;

@Test
public class ConfirmationsReceivedEventListenerTest {

    private ConfirmationReceivedEventListener confirmationReceivedEventListener;
    private QueueService queueService;
    private Confirmations confirmationsMock;
    private ConfirmationReceivedEventListener confirmationReceivedEventListenerWithMock;

    @BeforeMethod
    public void setUp() {
        queueService = mock(QueueService.class);
        Confirmations confirmations = new Confirmations();
        confirmationReceivedEventListener = new ConfirmationReceivedEventListener(queueService, confirmations);

        confirmationsMock = mock(Confirmations.class);
        confirmationReceivedEventListenerWithMock = new ConfirmationReceivedEventListener(queueService, confirmationsMock);
    }

    public void getConfirmationReceivedEvent_saveConfirmation() {
        Confirmation confirmation = new PlayerConfigurationConfirmation();

        ConfirmationReceivedEvent event = new ConfirmationReceivedEvent(this, confirmation);

        confirmationReceivedEventListenerWithMock.onApplicationEvent(event);

        verify(confirmationsMock).addConfirmation(confirmation);
    }

    public void get1ConfirmationReceivedEvent_doNotSendFinalConfigurationConfirmationToQueue_8() {
        Confirmation playerConfigurationConfirmation = new PlayerConfigurationConfirmation();

        ConfirmationReceivedEvent playerConfigurationConfirmationEvent = new ConfirmationReceivedEvent(this, playerConfigurationConfirmation);

        confirmationReceivedEventListener.onApplicationEvent(playerConfigurationConfirmationEvent);

        FinalConfigurationConfirmation finalConfigurationConfirmation = new FinalConfigurationConfirmation();
        verify(queueService, never()).sendObjectToQueue(Queues._8JudgeStartQueue, finalConfigurationConfirmation);
    }

    public void get3TheSameConfirmationReceivedEvent_doNotSendFinalConfigurationConfirmationToQueue_8() {
        Confirmation playerConfigurationConfirmation = new PlayerConfigurationConfirmation();

        ConfirmationReceivedEvent playerConfigurationConfirmationEvent = new ConfirmationReceivedEvent(this, playerConfigurationConfirmation);

        confirmationReceivedEventListener.onApplicationEvent(playerConfigurationConfirmationEvent);
        confirmationReceivedEventListener.onApplicationEvent(playerConfigurationConfirmationEvent);
        confirmationReceivedEventListener.onApplicationEvent(playerConfigurationConfirmationEvent);

        FinalConfigurationConfirmation finalConfigurationConfirmation = new FinalConfigurationConfirmation();
        verify(queueService, never()).sendObjectToQueue(Queues._8JudgeStartQueue, finalConfigurationConfirmation);
    }

    public void get3ConfirmationReceivedEvent_sendFinalConfigurationConfirmationToQueue_8() {
        Confirmation playerConfigurationConfirmation = new PlayerConfigurationConfirmation();
        Confirmation gameConfigurationConfirmation = new GameConfigurationConfirmation();
        Confirmation fleetPlacementConfirmation = new FleetPlacementConfirmation();

        ConfirmationReceivedEvent playerConfigurationConfirmationEvent = new ConfirmationReceivedEvent(this, playerConfigurationConfirmation);
        ConfirmationReceivedEvent gameConfigurationConfirmationEvent = new ConfirmationReceivedEvent(this, gameConfigurationConfirmation);
        ConfirmationReceivedEvent fleetPlacementConfirmationEvent = new ConfirmationReceivedEvent(this, fleetPlacementConfirmation);

        confirmationReceivedEventListener.onApplicationEvent(playerConfigurationConfirmationEvent);
        confirmationReceivedEventListener.onApplicationEvent(gameConfigurationConfirmationEvent);
        confirmationReceivedEventListener.onApplicationEvent(fleetPlacementConfirmationEvent);

        FinalConfigurationConfirmation finalConfigurationConfirmation = new FinalConfigurationConfirmation();
        verify(queueService).sendObjectToQueue(Queues._8JudgeStartQueue, finalConfigurationConfirmation);
    }


}
