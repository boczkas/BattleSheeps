package pl.jakubowskiprzemyslaw.REST.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class SessionService {

  public void addObjectToSession(HttpServletRequest request, Object object) {
    HttpSession session = request.getSession();
    session.setAttribute(object.getClass().getSimpleName(), object);
  }

  public boolean isObjectInSession(HttpServletRequest request, String objectName) {
    HttpSession session = request.getSession();
    Object attribute = session.getAttribute(objectName);
    Optional<Object> objectInSession = Optional.ofNullable(attribute);
    return objectInSession.isPresent();
  }
}
