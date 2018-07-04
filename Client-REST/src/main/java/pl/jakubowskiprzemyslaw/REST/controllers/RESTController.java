package pl.jakubowskiprzemyslaw.REST.controllers;

import pl.jakubowskiprzemyslaw.REST.services.QueueService;
import pl.jakubowskiprzemyslaw.REST.services.SessionService;

import javax.servlet.http.HttpServletRequest;

public class RESTController {

  private final QueueService queueService;
  private final SessionService sessionService;

  RESTController(QueueService queueService, SessionService sessionService) {
    this.queueService = queueService;
    this.sessionService = sessionService;
  }

  void sendMessageToQueue(String queueName, Object message) {
    queueService.sendMessageToQueue(queueName, message);
  }

  void addObjectToSession(HttpServletRequest request, Object object) {
    sessionService.addObjectToSession(request, object);
  }

  boolean isObjectInSession(HttpServletRequest request, String objectName) {
    return sessionService.isObjectInSession(request, objectName);
  }
}
