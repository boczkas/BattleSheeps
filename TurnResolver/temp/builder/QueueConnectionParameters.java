package pl.jakubowskiprzemyslaw.tajgertim.services.builder;

class QueueConnectionParameters {
  String login;
  String password;
  String playerQueueName;
  String turnQueueName;

  QueueConnectionParameters(String login, String password) {
    this.playerQueueName = QueueName.PLAYER.toString();
    this.turnQueueName = QueueName.SHOT.toString();
    this.login = login;
    this.password = password;
  }
}
