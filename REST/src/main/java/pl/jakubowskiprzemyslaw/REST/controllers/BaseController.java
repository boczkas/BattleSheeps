package pl.jakubowskiprzemyslaw.REST.controllers;

import pl.jakubowskiprzemyslaw.tajgertim.services.QueueService;
import pl.jakubowskiprzemyslaw.tajgertim.services.SessionService;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

  private final QueueService queueService;
  private final SessionService sessionService;

  BaseController(QueueService queueService, SessionService sessionService) {
    this.queueService = queueService;
    this.sessionService = sessionService;
  }

  void sendObjectToQueue(String queueName, Object object) {
    queueService.sendObjectToQueue(queueName, object);
  }

  void addObjectToSessionRequest(HttpServletRequest request, Object object) {
    sessionService.addObjectToSessionRequest(request, object);
  }

  boolean isObjectInSession(HttpServletRequest request, String objectName) {
    return sessionService.isObjectInSession(request, objectName);
  }
}
