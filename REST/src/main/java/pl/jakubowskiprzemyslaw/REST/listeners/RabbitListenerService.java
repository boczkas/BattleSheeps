package pl.jakubowskiprzemyslaw.REST.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubowskiprzemyslaw.REST.models.BoardsViewHandler;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;
import pl.jakubowskiprzemyslaw.tajgertim.services.BattleShipQueueInteractionHandler;

@BattleShipQueueInteractionHandler
public class RabbitListenerService {

  private final BoardsViewHandler handler;

  @Autowired
  public RabbitListenerService(BoardsViewHandler handler) {
    this.handler = handler;
  }

  @RabbitListener(queues = "PlayingBoardsViewQueue")
  public void getBoardsView(BoardsView boardsView) {
    handler.setBoardsView(boardsView);
  }
}
