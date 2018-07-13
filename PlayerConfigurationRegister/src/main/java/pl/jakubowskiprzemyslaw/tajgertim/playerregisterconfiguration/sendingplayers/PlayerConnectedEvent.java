package pl.jakubowskiprzemyslaw.tajgertim.playerregisterconfiguration.sendingplayers;

import org.springframework.context.ApplicationEvent;

class PlayerConnectedEvent extends ApplicationEvent {

  private static final long serialVersionUID = 570102554291869796L;

  PlayerConnectedEvent(Object source) {
    super(source);
  }
}
