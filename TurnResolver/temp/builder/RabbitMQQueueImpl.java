package pl.jakubowskiprzemyslaw.tajgertim.services.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQQueueImpl extends RabbitMQConnectionBuilder {

  private Logger logger = LoggerFactory.getLogger(RabbitMQQueueImpl.class);

  public void setConnectionParameters() {
    try {
      connection.setConnectionParams();
    } catch (IOException | TimeoutException e) {
      logger.error(e.getCause() + " " + e.getMessage());
    }
  }

  public void declareQueue() {
    try {
      connection.declareQueue();
    } catch (IOException e) {
      logger.error(e.getCause() + " " + e.getMessage());
    }
  }

  public String getMessageFromQueue(String queueName) {
    try {
      return connection.getMessageFromQueue(queueName);
    } catch (IOException e) {
      logger.error(e.getCause() + " " + e.getMessage());
      return "";
    }
  }

  public void sendMessageToQueue(String queueName, String message) {
    try {
      connection.sendMessage(queueName, message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeConnection() {
    try {
      connection.closeConnection();
    } catch (IOException | TimeoutException e) {
      logger.error(e.getCause() + " " + e.getMessage());
    }
  }

  public void deleteQueue() {
    try {
      connection.deleteQueue();
    } catch (IOException e) {
      logger.error(e.getCause() + " " + e.getMessage());
    }
  }

}
