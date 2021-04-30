package inf101.sem2.game;

import inf101.grid.Location;
import inf101.sem2.player.ConsolePlayer;
import inf101.sem2.player.DumbPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OthelloTest {

    Player player1;
    Player player2;
    Game game;

    @BeforeEach
    void thingsCommonAmongstTheTests() throws Exception {
        player1 = new DumbPlayer('Y');
        player2 = new DumbPlayer('Z');
        game = new Othello(new TerminalGraphics(), player1, player2);
    }

    @Test
    void testOthelloFlip() {
        Location player2DiscLocation = new Location(3, 3);
        Location player1LocationSetting = new Location(2, 3);

        game.board.set(player1LocationSetting, player1);
        Player flippedDisc = game.board.get(player2DiscLocation);
        assertEquals(flippedDisc, player1);
    }

    @Test
    void testGetPossibleMoves() {
        List<Location> moves = game.getPossibleMoves();
        Location locTrue = new Location(2,4);
        Location locFalse = new Location(1,1);
        boolean inBounds = moves.contains(locTrue);
        boolean outBounds = moves.contains(locFalse);

        assertEquals(true, inBounds);
        assertEquals(false, outBounds);
    }

    @Test
    void testIsWinner(){
        game.board.set(new Location(1, 1), player1);
        assertEquals(true, game.isWinner(player1));
    }
}