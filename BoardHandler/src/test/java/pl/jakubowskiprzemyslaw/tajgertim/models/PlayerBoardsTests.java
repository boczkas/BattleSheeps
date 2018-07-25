package pl.jakubowskiprzemyslaw.tajgertim.models;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.Board;
import pl.jakubowskiprzemyslaw.tajgertim.models.board.NoMastAtPositionException;
import pl.jakubowskiprzemyslaw.tajgertim.models.coordinates.Coordinate;
import pl.jakubowskiprzemyslaw.tajgertim.models.shoot.ShootResult;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

@Test
public class PlayerBoardsTests {

    private PlayerBoards boards;
    private Board board;
    private PlayerShots shots;
    private static final int COORDINATE_X = 1;
    private static final int COORDINATE_Y = 1;

    @BeforeMethod
    public void setUp() {
        board = mock(Board.class);
        shots = mock(PlayerShots.class);
        boards = new PlayerBoards(board, shots);
    }

    public void verifyIfMarkHitOnShip_callsMarkHit_onBoard_Test() throws NoMastAtPositionException {
        Coordinate coordinate = new Coordinate(1, 1);
        boards.markHitOnShip(coordinate);
        verify(board).markHit(coordinate);
    }

    public void verifyIfMarkMissOnShotsBoard_callsAdd_onPlayerShots_Test() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        boards.markMissOnShotsBoard(coordinate);
        verify(shots).add(coordinate, ShootResult.MISS);
    }

    public void verifyIfMarkHitOnShotsBoard_callsAdd_onPlayerShots_Test() {
        Coordinate coordinate = new Coordinate(COORDINATE_X, COORDINATE_Y);
        boards.markHitOnShotsBoard(coordinate);
        verify(shots).add(coordinate, ShootResult.HIT);
    }

    public void verifyIfMarkMissOnShotsBoard_callsAdd_onPlayerShots_WhenCoordinateIsNull_Test() {
        Coordinate coordinate = null;
        boards.markMissOnShotsBoard(coordinate);
        verify(shots).add(coordinate, ShootResult.MISS);
    }

    public void verifyIfGetBoard_ReturnsCorrectBoard_Test() {
        Board board = boards.getBoard();
        assertEquals(board, this.board);
    }

    public void verifyIfGetPlayerShotsMap_callsGetCoordinateShootResultMap_Test() {
        boards.getPlayerShotsMap();
        verify(shots).getCoordinateShootResultMap();
    }
}
