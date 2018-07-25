package pl.jakubowskiprzemyslaw.tajgertim.models;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Fleet;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Ship;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.FieldState;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

@Test
public class PlayersBoardsTest {

    private PlayersBoards playersBoards;
    private static final int MAP_SIZE_WITH_TWO_PAIRS = 2;
    private static final int COORDINATE_Y = 1;
    private static final int COORDINATE_X = 1;
    private PlayerBoards playerBoards;
    private Player player;
    private Board board;
    private PlayerShots playerShots;

    @BeforeMethod
    public void setUp() {
        playersBoards = new PlayersBoards();
        playersBoards.addBoardsForPlayer(player, playerBoards);
    }

    public void verifyIfAddBoardsForPlayer_AddsElementsToMap_Test() {
        player = new Player("", "");
        playerBoards = mock(PlayerBoards.class);
        playersBoards.addBoardsForPlayer(player, playerBoards);
        assertEquals(MAP_SIZE_WITH_TWO_PAIRS, playersBoards.getSize());
    }

    public void verifyIfAddBoardsForPlayer_AddsElementsToMap_WhenPlayerIsNull_Test() {
        Player player = null;
        playersBoards.addBoardsForPlayer(player, mock(PlayerBoards.class));
        assertEquals(MAP_SIZE_WITH_TWO_PAIRS, playersBoards.getSize());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void verifyIfMarkHitAtShip_ThrowsNPE_WhenCoordinateIsNull_Test() throws NoMastAtPositionException {
        playersBoards = new PlayersBoards();
        playersBoards.markHitAtShip(player, null);
    }

    public void verifyIfMarkHitAtShip_CallsMarkHitOnShip_Test() throws NoMastAtPositionException {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        playersBoards.markHitAtShip(player, coordinate);
        verify(playerBoards).markHitOnShip(coordinate);
    }

    public void verifyIfMarkHitAtPlayerShotBoard_CallsHitOnShotsBoard_Test() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        playersBoards.markHitAtPlayerShotBoard(player, coordinate);
        verify(playerBoards).markHitOnShotsBoard(coordinate);
    }

    public void verifyIfMarkMissOnBoard_CallsMarkMissOnShotsBoard_Test() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        playersBoards.markMissOnBoard(player, coordinate);
        verify(playerBoards).markMissOnShotsBoard(coordinate);
    }

    public void verifyIfAddFleetForExistingPlayer_CallsPutOnMap_AndDoesNotIncrementMapSize_Test() {
        playersBoards.addFleetForPlayer(player, mock(Fleet.class));
        assertEquals(1, playersBoards.getSize());
    }

    public void verifyIfAddFleetForNonExistingPlayer_CallsPutOnMap_AndIncrementsMapSize_Test() {
        Player player1 = new Player("test", "test");
        playersBoards.addFleetForPlayer(player1, mock(Fleet.class));
        assertEquals(2, playersBoards.getSize());
    }

    public void verifyIfAddPlayerWithExistingPlayer_CallsPutOnMap_AndDoseNotIncrementMapSize_Test() {
        playersBoards.addPlayer(player);
        assertEquals(1, playersBoards.getSize());
    }

    public void verifyIfAddPlayerWithNonExistingPlayer_CallsPutOnMap_AndIncrementsMapSize_Test() {
        Player player1 = new Player("test", "test");
        playersBoards.addPlayer(player1);
        assertEquals(2, playersBoards.getSize());
    }

    public void verifyIfGetFieldStatus_ReturnsEmptyFieldState_Test() {
        prepareBoardWithOneShip();
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        FieldState fieldStatus = playersBoards.getFieldStatus(player, coordinate);
        assertEquals(fieldStatus, FieldState.EMPTY);
    }

    public void verifyIfGetBoard_ReturnsCorrectPlayerBoard_Test() {
        prepareBoardWithOneShip();
        Board playerBoard = playersBoards.getBoard(player);
        assertEquals(board, playerBoard);
    }

    public void verifyIfGetPlayerShotsMap_ReturnsCorrectPlayerShotsEmptyMap_Test() {
        prepareBoardWithOneShip();
        Map<Coordinate, ShootResult> playerShotsMap = playersBoards.getPlayerShotsMap(player);
        assertEquals(playerShots.getCoordinateShootResultMap(), playerShotsMap);
    }

    private void prepareBoardWithOneShip() {
        playersBoards = new PlayersBoards();
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship());

        board = new Board(new Fleet(ships));
        playerShots = new PlayerShots();
        playerShots.add(new Coordinate(2,2), ShootResult.MISS);
        playersBoards.addBoardsForPlayer(player, new PlayerBoards(board, playerShots));
    }

}
