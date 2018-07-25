package pl.jakubowskiprzemyslaw.tajgertim.models;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.room.Room;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test
public class AllRoomOpponentsTest {

    private AllRoomsOpponents allRoomsOpponents;
    private static final int SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM = 1;

    @BeforeMethod
    public void setUp() {
        allRoomsOpponents = new AllRoomsOpponents();
    }

    public void addCorrectOpponents_PassesWithoutExceptionTest() {
        Player p1 = new Player("", "");
        Player p2 = new Player("", "");

        allRoomsOpponents.addOpponents(p1, p2);
        assertNotNull(allRoomsOpponents.getAllRoomsOpponents());
        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addNullPlayerAsOpponent_Test() {
        Player p1 = new Player("", "");
        Player p2 = null;

        allRoomsOpponents.addOpponents(p1, p2);
        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addTwoNullPlayersAsOpponent_Test() {
        Player p1 = null;
        Player p2 = null;

        allRoomsOpponents.addOpponents(p1, p2);
        assertNotNull(allRoomsOpponents.getAllRoomsOpponents());
    }

    public void getCorrectOpponentFromRoom_Test() throws NoSuchPlayerException {
        Player p1 = new Player("", "");
        Player p2 = new Player("", "");

        allRoomsOpponents.addOpponents(p1, p2);
        Player opponent = allRoomsOpponents.getOpponent(p1);

        assertEquals(p2, opponent);
    }

    @Test(expectedExceptions = NoSuchPlayerException.class)
    public void getIncorrectOpponentFormRoom_Test() throws NoSuchPlayerException {
        Player p1 = new Player("", "");
        Player p2 = new Player("", "");
        Player p3 = new Player("test", "test");

        allRoomsOpponents.addOpponents(p1, p2);
        allRoomsOpponents.getOpponent(p3);
    }

    public void getNullOpponentFormRoom_Test() throws NoSuchPlayerException {
        Player p1 = new Player("", "");
        Player p2 = null;

        allRoomsOpponents.addOpponents(p1, p2);
        Player opponent = allRoomsOpponents.getOpponent(p2);
        assertEquals(p1, opponent);
        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addNewPlayerToNewRoom_Test() {
        Player p1 = new Player("", "");
        Room room = new Room("test");

        allRoomsOpponents.addPlayerToRoom(room, p1);
        assertNotNull(allRoomsOpponents.getAllRoomsOpponents());
        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addNewPlayerToExistingRoom_Test() {
        Player p1 = new Player("", "");
        Room room = new Room("test");
        allRoomsOpponents.addPlayerToRoom(room, p1);
        Player p2 = new Player("", "");

        allRoomsOpponents.addPlayerToRoom(room, p2);

        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addNewPlayerToNullRoom_Test() {
        Player p1 = new Player("", "");
        Room room = null;
        allRoomsOpponents.addPlayerToRoom(room, p1);

        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addTwoNullPlayersToOneRoom_AndGetOpponent_Test() throws NoSuchPlayerException {
        Player p1 = null;
        Player p2 = null;
        allRoomsOpponents.addOpponents(p1, p2);

        Player opponent = allRoomsOpponents.getOpponent(p2);
        assertEquals(p1, opponent);

        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }

    public void addMoreThanTwoPlayersToRoom_Test() {  //TODO: da fuck? three opponents in one room?
        Player p1 = new Player("", "");
        Player p2 = new Player("", "");
        Player p3 = new Player("", "");
        Room room = new Room("");

        allRoomsOpponents.addPlayerToRoom(room, p1);
        allRoomsOpponents.addPlayerToRoom(room, p2);
        allRoomsOpponents.addPlayerToRoom(room, p3);

        int sizeOfAllRoomOpponents = allRoomsOpponents.getAllRoomsOpponents().size();
        assertEquals(SIZE_OF_ALL_ROOM_OPPONENTS_WITH_ONE_ROOM, sizeOfAllRoomOpponents);
    }
}
