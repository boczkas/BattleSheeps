package pl.jakubowskiprzemyslaw.REST.services;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class PlayerService {

    public Player getPlayerFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String className = getPlayerClassSimpleName();

        return (Player) session.getAttribute(className);
    }

    private String getPlayerClassSimpleName() {
        Class playerClass = Player.class;
        return playerClass.getSimpleName();
    }
}
