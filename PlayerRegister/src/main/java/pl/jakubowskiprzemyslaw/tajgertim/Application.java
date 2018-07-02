package pl.jakubowskiprzemyslaw.tajgertim;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Application {

    public static void main(String[] args) throws IOException, TimeoutException {
        ServerController serverController = new ServerController();
        serverController.run();
    }

}
