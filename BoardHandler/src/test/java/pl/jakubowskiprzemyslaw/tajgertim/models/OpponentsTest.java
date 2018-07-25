package pl.jakubowskiprzemyslaw.tajgertim.models;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakubowskiprzemyslaw.tajgertim.models.player.Player;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class OpponentsTest {

    private Opponents opponents;

    @BeforeMethod
    public void setUp() {
        Player player = new Player("", "");
        opponents = new Opponents(player);
    }

    public void sizeOfList_AfterAddingNewPlayer_ShouldBeMoreThanZero_Test() {
        assertFalse(opponents.isEmpty());
    }

    public void sizeOfList_AfterAddingTwoPlayers_ShouldBeEqualsTwo() {
        Player p1 = new Player("", "");
        Player p2 = new Player("", "");

        opponents = new Opponents(p1, p2);
        assertFalse(opponents.isEmpty());
    }

    public void containsWithExistingPlayer_ShouldReturnTrue() {
        Player player = new Player("", "");
        assertTrue(opponents.contains(player));
    }

    public void containsWithNotExistingPlayer_ShouldReturnFalse() {
        assertFalse(opponents.contains(new Player("test", "test")));
    }
}
