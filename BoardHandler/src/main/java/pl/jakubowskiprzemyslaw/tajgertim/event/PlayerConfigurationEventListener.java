package pl.jakubowskiprzemyslaw.tajgertim.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.configuration.PlayerConfiguration;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;
import pl.jakubowskiprzemyslaw.tajgertim.service.BoardHandler;

@Component
public class PlayerConfigurationEventListener implements ApplicationListener<PlayerConfigurationEvent> {

    private final BoardHandler boardHandler;

    @Autowired
    public PlayerConfigurationEventListener(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
    }

    @Override
    public void onApplicationEvent(PlayerConfigurationEvent event) {
        PlayerConfiguration playerConfiguration = event.getPlayerConfiguration();
        Player player = playerConfiguration.getPlayer();
        boardHandler.addPlayer(player);
        String roomName = "roomName";
        boardHandler.addPlayerToRoom(new Room(roomName), player);
    }
}
