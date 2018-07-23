package pl.jakubowskiprzemyslaw.REST.services;

import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;

@Test
public class FleetGeneratorServiceTest {

    @Test(invocationCount = 30)
    public void testFleetPlacement() {
        FleetGeneratorService fleetGeneratorService = new FleetGeneratorService();
        Fleet fleet = fleetGeneratorService.generateRandomFleet();
        Board board = new Board(fleet);

        System.out.println(board.getConsoleView(10));
    }


}
