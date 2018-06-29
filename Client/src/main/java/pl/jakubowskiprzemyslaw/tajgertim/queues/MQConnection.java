package pl.jakubowskiprzemyslaw.tajgertim.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQConnection {

  private static Connection connection;

  public static Channel openConnection() throws IOException, TimeoutException {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername("admin");
    factory.setPassword("admin");
    factory.setHost("10.30.1.140");

    connection = factory.newConnection();
    return connection.createChannel();
  }

  public static void closeConnection(Channel channel) throws IOException, TimeoutException {
    channel.close();
    connection.close();
  }
}
