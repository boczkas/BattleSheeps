package pl.jakubowskiprzemyslaw.REST.models;

import org.springframework.stereotype.Component;
import pl.jakubowskiprzemyslaw.tajgertim.models.view.BoardsView;

import java.util.Optional;

@Component
public class BoardsViewHandler {

  private BoardsView view;

  public void setBoardsView(BoardsView boardsView) {
    this.view = boardsView;
  }

  public Optional<BoardsView> getView() {
    return view == null ? Optional.empty() : Optional.of(view);
  }

  public void clear() {
    view = null;
  }
}
