package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;

@Service
public class PlayerService {

    public void setPlayerIP(Player player, String IP) {
        player.setIP(IP);
    }
}
