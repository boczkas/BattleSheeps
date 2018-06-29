package pl.jakubowskiprzemyslaw.tajgertim.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Component
public class MQConnection {

  private Connection connection;

  public Channel openConnection() throws IOException, TimeoutException {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername("admin");
    factory.setPassword("admin");
    factory.setHost("10.30.1.140");

    connection = factory.newConnection();
    return connection.createChannel();
  }

  public void closeConnection(Channel channel) throws IOException, TimeoutException {
    channel.close();
    if(connection.isOpen()) {
      connection.close();
    }
  }
}
