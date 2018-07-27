package pl.jakubowskiprzemyslaw.REST.listeners;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubowskiprzemyslaw.REST.services.JSONService;
import pl.jakubowskiprzemyslaw.REST.services.FrontendMessageSenderService;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.PlayingPlayer;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class RabbitListenerService {

    private final LoggerService logger;
    private final FrontendMessageSenderService frontendMessageSenderService;
    private final JSONService jsonService;

    @Autowired
    public RabbitListenerService(LoggerService logger, FrontendMessageSenderService frontendMessageSenderService, JSONService jsonService) {
        this.logger = logger;
        this.frontendMessageSenderService = frontendMessageSenderService;
        this.jsonService = jsonService;
    }

    @RabbitListener(queues = "PlayingBoardsViewQueue")
    public void getBoardsView(BoardsView boardsView) {
        logger.logInfo(RabbitListenerService.class, boardsView.toString());

        ObjectNode shotsNode = jsonService.createJSONNodeForPlayerShots(boardsView);
        ObjectNode shipsNode = jsonService.createJSONNodeWithShips(boardsView);

        frontendMessageSenderService.sendBoards(boardsView.getPlayer().getName(), shotsNode);
        frontendMessageSenderService.sendBoards(boardsView.getPlayer().getName(), shipsNode);
    }

    @RabbitListener(queues = "PlayingPlayerQueue") //19
    public void getPlayingPlayer(PlayingPlayer playingPlayer) {
        String id = playingPlayer.getPlayerName();
        frontendMessageSenderService.sendMessage(id, playingPlayer.getPlayerName());
    }
}
