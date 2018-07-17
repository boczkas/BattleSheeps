package pl.jakubowskiprzemyslaw.REST.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.REST.services.JSONService;
import pl.jakubowskiprzemyslaw.REST.services.PlayerService;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class BoardsHandlerController {

    private final BoardsViewHandler boardsViewHandler;
    private ObjectMapper mapper;
    private final JSONService jsonService;
    private final PlayerService playerService;

    @Autowired
    public BoardsHandlerController(BoardsViewHandler boardsViewHandler, ObjectMapper mapper, JSONService jsonService, PlayerService playerService) {
        this.boardsViewHandler = boardsViewHandler;
        this.mapper = mapper;
        this.jsonService = jsonService;
        this.playerService = playerService;
    }

    @PostMapping(value = "/getBoardView")
    public @ResponseBody
    ObjectNode getBoardView(HttpServletRequest request) {

        Optional<BoardsView> optionalBoardsView = boardsViewHandler.getView();
        Player sessionPlayer = playerService.getPlayerFromRequest(request);
        Optional<Player> currentPlayer = boardsViewHandler.getPlayer();
        ObjectNode node = mapper.createObjectNode();

        boolean isPlayerPresent = currentPlayer.isPresent();
        if (isPlayerPresent && currentPlayer.get().equals(sessionPlayer)) {
            node = jsonService.createJSONNodeWithShips(optionalBoardsView.get());
        }

        return node;
    }

    @PostMapping(value = "/getShotBoardView")
    public @ResponseBody
    ObjectNode getShotBoardView(HttpServletRequest request) {

        Optional<BoardsView> optionalBoardsView = boardsViewHandler.getView();
        Player sessionPlayer = playerService.getPlayerFromRequest(request);
        Optional<Player> currentPlayer = boardsViewHandler.getPlayer();
        ObjectNode node = mapper.createObjectNode();

        boolean isPlayerPresent = currentPlayer.isPresent();
        if (isPlayerPresent && currentPlayer.get().equals(sessionPlayer)) {
            node = jsonService.createJSONNodeForPlayerShots(optionalBoardsView.get());
            boardsViewHandler.clear();
        }

        return node;
    }
}
