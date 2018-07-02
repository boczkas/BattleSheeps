package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    public void savePlayerToSession(Player player, HttpSession session) {
        session.setAttribute("player", player);
    }
}
