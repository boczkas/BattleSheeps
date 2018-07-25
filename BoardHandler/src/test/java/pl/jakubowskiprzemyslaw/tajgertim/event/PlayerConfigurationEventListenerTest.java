package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Test
public class PlayerConfigurationEventListenerTest {

    private PlayerConfigurationEventListener listener;
    private BoardHandler boardHandler;

    @BeforeMethod
    public void setUp() {
        boardHandler = mock(BoardHandler.class);
        listener = new PlayerConfigurationEventListener(boardHandler);
    }

    public void sendingPlayerConfiguration_AddsPlayer() {
        Player player = new Player("", "");
        PlayerConfiguration configuration = new PlayerConfiguration(player);
        PlayerConfigurationEvent playerConfigurationEvent = new PlayerConfigurationEvent(this, configuration);
        listener.onApplicationEvent(playerConfigurationEvent);

        verify(boardHandler).addPlayer(player);
        verify(boardHandler).addPlayerToRoom(new Room("1"), player);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void sendingObject_throwsClassCastException() {
        Object object = new Object();
        PlayerConfigurationEvent event = new PlayerConfigurationEvent(this, (PlayerConfiguration) object);
        listener.onApplicationEvent(event);
    }

}
