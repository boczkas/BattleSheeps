package pl.jakubowskiprzemyslaw.tajgertim.services.builder;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

class RabbitMQConnection extends QueueConnectionParameters {

  private Channel channel;
  private Connection connection;

  RabbitMQConnection(String login, String password) {
    super(login, password);
  }

  void setConnectionParams() throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername(login);
    factory.setPassword(password);
    factory.setHost("10.30.1.140");
    connection = factory.newConnection();
  }

  void declareQueue() throws IOException {
    channel = connection.createChannel();
    channel.queueDeclare(playerQueueName, false, false, false, null);
    channel.queueDeclare(turnQueueName, false, false, false, null);
  }

  String getMessageFromQueue(String queueName) throws IOException {
    GetResponse getResponse = channel.basicGet(queueName, true);
    if(getResponse != null)
      return new String(getResponse.getBody(), "UTF-8");

    return "";
  }

  void sendMessage(String queueName, String message) throws IOException {
    byte[] messageBytes = message.getBytes();
    channel.basicPublish("", queueName, null, messageBytes);
  }

  void deleteQueue() throws IOException {
    channel.queueDelete(playerQueueName);
  }

  void closeConnection() throws IOException, TimeoutException {
    if(channel.isOpen() && connection.isOpen()) {
      channel.close();
      connection.close();
    }
  }
}
