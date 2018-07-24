package pl.jakubowskiprzemyslaw.tajgertim.service;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.AllRoomsOpponents;
import pl.jakubowskiprzemyslaw.tajgertim.models.NoSuchPlayerException;
import pl.jakubowskiprzemyslaw.tajgertim.models.PlayersBoards;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

@Test
public class BoardHandlerServiceTest {

    private BoardHandler boardHandler;
    private PlayersBoards boards;
    private Player p1;
    private Player p2;

    private static final int COORDINATE_X = 1;
    private static final int COORDINATE_Y = 1;
    private static final int SIZE_OF_MAP_WITH_TWO_ROOMS = 2;
    private AllRoomsOpponents opponents;

    @BeforeMethod
    public void setUp() {
        boards = mock(PlayersBoards.class);
        opponents = new AllRoomsOpponents();

        p1 = new Player("Player1","");
        p2 = new Player("Player2","");

        opponents.addOpponents(p1, p2);
        boardHandler = new BoardHandler(opponents, boards);
    }

    public void verifyIfMarkHit_CallsMarkHitAtShip_AndMarkHitAtPlayerShotBoard_Test() throws NoMastAtPositionException, NoSuchPlayerException {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        boardHandler.markHit(p1, coordinate);
        verify(boards).markHitAtShip(p2, coordinate);
        verify(boards).markHitAtPlayerShotBoard(p1, coordinate);
    }

    public void verifyIfMarkMiss_CallsMarkMissOnBoard_Test() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        boardHandler.markMiss(p1, coordinate);
        verify(boards).markMissOnBoard(p1, coordinate);
    }

    public void verifyIfMarkMiss_CallsMarkMissOnBoard_WhenCoordinateIsNull_Test2() {
        boardHandler.markMiss(p1, null);
        verify(boards).markMissOnBoard(p1, null);
    }

    @Test(expectedExceptions = NoSuchPlayerException.class)
    public void verifyIfMarkHit_ThrowsException_IfPlayerHasNoOpponent_Test() throws NoMastAtPositionException, NoSuchPlayerException {
        Player p3 = new Player("", "");
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        boardHandler.markHit(p3, coordinate);
    }

    public void verifyIfGetOpponentFieldStatus_CallsGetFieldStatusInPlayersBoards_Test() throws NoSuchPlayerException {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        boardHandler.getOpponentFieldStatus(p1, coordinate);
        verify(boards).getFieldStatus(p2, coordinate);
    }

    public void verifyIfGetPlayerBoard_CallsGetBoardInPlayersBoards_Test() {
        boardHandler.getPlayerBoard(p1);
        verify(boards).getBoard(p1);
    }

    public void verifyIfGetPlayerShotsMap_CallsGetPlayerShotsMapInPlayersBoards_Test() {
        boardHandler.getPlayerShotsMap(p1);
        verify(boards).getPlayerShotsMap(p1);
    }

    public void verifyIfAddPlayer_CallsAddPlayerInPlayersBoards_Test() {
        Player testPlayer = new Player("", "");
        boardHandler.addPlayer(testPlayer);
        verify(boards).addPlayer(testPlayer);
    }

    public void verifyIfAddFleetForExistingPlayer_CallsAddFleetForPlayerInPlayersBoards_Test() {
        Fleet fleet = mock(Fleet.class);
        boardHandler.addFleetForPlayer(p1, fleet);
        verify(boards).addFleetForPlayer(p1, fleet);
    }

    public void verifyIfAddFleetForNotExistingPlayer_CallsAddFleetForPlayerInPlayersBoards_Test() {
        Player tempPlayer = new Player("", "");
        Fleet fleet = mock(Fleet.class);
        boardHandler.addFleetForPlayer(tempPlayer, fleet);
        verify(boards).addFleetForPlayer(tempPlayer, fleet);
    }

    public void verifyIfAddExistingPlayerToRoom_CallsAddPlayerToRoomInAllRoomsOpponents_Test() {
        Room room = new Room("testRoom");
        boardHandler.addPlayerToRoom(room, p1);
        int mapSize = opponents.getAllRoomsOpponents().size();
        assertEquals(mapSize, SIZE_OF_MAP_WITH_TWO_ROOMS);
    }

    public void verifyIfAddNotExistingPlayerToRoom_CallsAddPlayerToRoomInAllRoomsOpponents_Test() {
        Player tempPlayer = new Player("", "");
        Room room = new Room("testRoom");
        boardHandler.addPlayerToRoom(room, tempPlayer);
        int mapSize = opponents.getAllRoomsOpponents().size();
        assertEquals(mapSize, SIZE_OF_MAP_WITH_TWO_ROOMS);
    }

}
