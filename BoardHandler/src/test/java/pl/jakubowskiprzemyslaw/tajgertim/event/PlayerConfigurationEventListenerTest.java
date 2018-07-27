package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class PlayerConfigurationEventListenerTest {

    private PlayerConfigurationEventListener listener;
    private BoardHandler boardHandler;
    private QueueService queueService;

    @BeforeMethod
    public void setUp() {
        boardHandler = mock(BoardHandler.class);
        queueService = mock(QueueService.class);
        listener = new PlayerConfigurationEventListener(boardHandler, queueService);
    }

    public void sendingPlayerConfiguration_AddsPlayer() {
        Player player = new Player("", "");
        PlayerConfiguration configuration = new PlayerConfiguration(player);
        PlayerConfigurationEvent playerConfigurationEvent = new PlayerConfigurationEvent(this, configuration);
        listener.onApplicationEvent(playerConfigurationEvent);

        verify(boardHandler).addPlayer(player);
        String roomName = "roomName";
        verify(boardHandler).addPlayerToRoom(new Room(roomName), player);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object object = new Object();
        PlayerConfigurationEvent event = new PlayerConfigurationEvent(this, (PlayerConfiguration) object);
        listener.onApplicationEvent(event);
    }

}
