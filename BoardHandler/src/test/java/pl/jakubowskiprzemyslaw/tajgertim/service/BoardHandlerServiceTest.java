package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.AllRoomsOpponents;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersBoards;

import static org.mockito.Mockito.mock;

@Test
public class BoardHandlerServiceTest {

    private BoardHandler boardHandler;
    private PlayersBoards boards;
    private AllRoomsOpponents opponents;


    @BeforeMethod
    public void setUp() {
        boards = mock(PlayersBoards.class);
        opponents = mock(AllRoomsOpponents.class);
        //boardHandler = new BoardHandler();
    }

}
