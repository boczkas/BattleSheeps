package pl.jakubowskiprzemyslaw.REST.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;
import pl.jakubowskiprzemyslaw.tajgertim.services.LoggerService;

@BattleShipQueueInteractionHandler
public class RabbitListenerService {

  private final BoardsViewHandler handler;
  private final LoggerService logger;

  @Autowired
  public RabbitListenerService(BoardsViewHandler handler, LoggerService logger) {
    this.handler = handler;
    this.logger = logger;
  }

  @RabbitListener(queues = "PlayingBoardsViewQueue")
  public void getBoardsView(BoardsView boardsView) {
    logger.logInfo(RabbitListenerService.class, boardsView.toString());
    handler.setBoardsView(boardsView);
  }
}
