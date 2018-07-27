package pl.jakubowskiprzemyslaw.REST.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubowskiprzemyslaw.REST.controllers.PlayerNameHandler;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.REST.services.JSONService;
import pl.jakubowskiprzemyslaw.REST.services.PlayerService;
import pl.jakubowskiprzemyslaw.REST.utils.FrontendMessageSender;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.PlayingPlayer;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

import java.util.Optional;

@BattleShipQueueInteractionHandler
public class RabbitListenerService {

    private final BoardsViewHandler handler;
    private final LoggerService logger;
    private final FrontendMessageSender frontendMessageSender;
    private final JSONService jsonService;

    @Autowired
    public RabbitListenerService(BoardsViewHandler handler, LoggerService logger, FrontendMessageSender frontendMessageSender, JSONService jsonService) {
        this.handler = handler;
        this.logger = logger;
        this.frontendMessageSender = frontendMessageSender;
        this.jsonService = jsonService;
    }

    @RabbitListener(queues = "PlayingBoardsViewQueue")
    public void getBoardsView(BoardsView boardsView) {
        logger.logInfo(RabbitListenerService.class, boardsView.toString());
//        handler.setBoardsView(boardsView);

        ObjectNode node;
        node = jsonService.createJSONNodeForPlayerShots(boardsView);

        frontendMessageSender.sendBoards(boardsView.getPlayer().getName(), node);
    }

    @RabbitListener(queues = "PlayingPlayerQueue") //19
    public void getPlayingPlayer(PlayingPlayer playingPlayer) { //add if
        String id = playingPlayer.getPlayerName();
        frontendMessageSender.sendMessage(id, playingPlayer.getPlayerName());
    }
}
