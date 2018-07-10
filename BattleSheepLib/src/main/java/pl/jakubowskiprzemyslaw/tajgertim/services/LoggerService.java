package pl.jakubowskiprzemyslaw.tajgertim.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {
    private static final Logger logger = LoggerFactory.getLogger("BattleSheep");

    public void logError(String message){
        logger.error(message);
    }

    public void logInfo(Class shotHandlerClass, String message) {
        logger.info("[" + shotHandlerClass.getSimpleName() + "]: " +  message);
    }
}
