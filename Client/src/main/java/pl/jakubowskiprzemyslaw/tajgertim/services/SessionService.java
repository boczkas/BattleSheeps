package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.tajgertim.models.Player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    public Player getPlayerFromSession(HttpServletRequest request) {
        return (Player) request.getSession().getAttribute("player");
    }

    public void savePlayerToSession(Player player, HttpSession session) {
        session.setAttribute("player", player);
    }
}
