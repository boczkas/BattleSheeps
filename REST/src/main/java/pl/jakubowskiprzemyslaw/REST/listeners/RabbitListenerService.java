package pl.jakubowskiprzemyslaw.REST.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubowskiprzemyslaw.REST.controllers.PlayerNameHandler;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.REST.utils.SendMessage;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class RabbitListenerService {

  private final BoardsViewHandler handler;
  private final LoggerService logger;
  private final SendMessage sendMessage;
  private final PlayerNameHandler playerNameHandler;

  @Autowired
  public RabbitListenerService(BoardsViewHandler handler, LoggerService logger, SendMessage sendMessage, PlayerNameHandler playerNameHandler) {
    this.handler = handler;
    this.logger = logger;
    this.sendMessage = sendMessage;
    this.playerNameHandler = playerNameHandler;
  }

  @RabbitListener(queues = "PlayingBoardsViewQueue")
  public void getBoardsView(BoardsView boardsView) {
    logger.logInfo(RabbitListenerService.class, boardsView.toString());
    handler.setBoardsView(boardsView);
  }

  @RabbitListener(queues = "TestQueue") //19
  public void getTest(String message) { //add if
    String id = playerNameHandler.getName();
    sendMessage.sendMessage(id, message);
  }
}
