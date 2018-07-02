//package pl.jakubowskiprzemyslaw.tajgertim.services;
//
//import pl.jakubowskiprzemyslaw.tajgertim.services.builder.RabbitMQConnectionBuilder;
//
//public class RabbitMQConnector {
//  private RabbitMQConnectionBuilder builder;
//
//  RabbitMQConnector(RabbitMQConnectionBuilder builder) {
//    this.builder = builder;
//  }
//
//  void connect(String login, String password) {
//    builder.connect(login, password);
//  }
//
//  String getMessage(String queueName) {
//    return builder.getMessageFromQueue(queueName);
//  }
//
//  void sendMessage(String queueName, String message) {
//    builder.sendMessageToQueue(queueName, message);
//  }
//
//  public void deleteQueue() {
//    builder.deleteQueue();
//  }
//
//  public void closeConnection() {
//    builder.closeConnection();
//  }
//}
