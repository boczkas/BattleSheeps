package pl.jakubowskiprzemyslaw.tajgertim.services.builder;

public abstract class RabbitMQConnectionBuilder {

  RabbitMQConnection connection;

  public void connect(String login, String password) {
    connection = new RabbitMQConnection(login, password);
    setConnectionParameters();
    declareQueue();
  }

  public abstract void setConnectionParameters();

  public abstract void declareQueue();

  public abstract String getMessageFromQueue(String queueName);

  public abstract void sendMessageToQueue(String queueName, String message);

  public abstract void closeConnection();

  public abstract void deleteQueue();

}
