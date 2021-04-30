package inf101.sem2.game;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Othello extends Game {


    private void startingBoard() {
        board.set(new Location(3, 3), getCurrentPlayer());
        board.set(new Location(4, 4), getCurrentPlayer());
        players.nextPlayer();
        board.set(new Location(3, 4), getCurrentPlayer());
        board.set(new Location(4, 3), getCurrentPlayer());
        players.nextPlayer();
    }

    public Othello(Graphics graphics, Player player1, Player player2) {
        this(graphics);
        players.add(player1);
        players.add(player2);
        startingBoard();
    }

    public Othello(Graphics graphics) {
        super(new GameBoard(8, 8), graphics);
    }

    public Othello(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);
        startingBoard();
    }

    /**
     * Here we check if the location selected can be place on the board.
     * <p>
     * It looks at the getPossibleMoves() method and evaluvates if the list is empty or not.
     * If the list is empty there is no place for the player to place his disc and the turn moves on.
     * <p>
     * And if the list has the location that the method is given, it is a valid move.
     *
     * @param loc
     */
    @Override
    public void makeMove(Location loc) {
        if (getPossibleMoves().isEmpty()) {
            players.nextPlayer();
            samePlayer = false;
        } else if (!canPlace(loc)) {
            throw new IllegalArgumentException("Can not make that move");
        } else if (locationInList(loc)) {
            board.set(loc, getCurrentPlayer());
            flipper(loc);
            players.nextPlayer();
            samePlayer = false;
        } else {
            samePlayer = true;
        }

    }

    /**
     * This boolean keeps track of the player making his move.
     * <p>
     * The reason for this is because if you click an invalid tile, we need to make sure that the method noMoreMoves
     * doesn't run twice in a row when it still is the same player.
     */
    private boolean samePlayer = false;

    private boolean locationInList(Location loc) {
        for (Location locationCheck : getPossibleMoves()) {
            if (loc.equals(locationCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method finds all the empty tiles and check their neighbour for any discs of the other player.
     * And if this is the case it continues in the same direction until it reaches one of it own discs.
     * Now if it reaches one of it own discs it adds the original empty location to the list that is returned.
     *
     * @return a list all possible location the current player can place their discs
     */
    @Override
    public List<Location> getPossibleMoves() {
        List<Location> possibleMoves = new ArrayList<>();
        for (Location startingLocations : super.getPossibleMoves()) {
            for (GridDirection someDirections : GridDirection.EIGHT_DIRECTIONS) {
                Location movedLocation = startingLocations;
                boolean firstIteration = true;
                while (true) {
                    movedLocation = movedLocation.getNeighbor(someDirections);
                    if (!board.isOnGrid(movedLocation)) {
                        break;
                    } else if (board.get(movedLocation) == null) {
                        break;
                    } else if (board.get(movedLocation) == getCurrentPlayer()) {
                        if (firstIteration) {
                            break;
                        } else {
                            if (!possibleMoves.contains(startingLocations)) {
                                possibleMoves.add(startingLocations);
                            }
                        }
                    } else {
                        firstIteration = false;
                    }
                }
            }
        }
        return possibleMoves;
    }

    /**
     * This method as a similar algorithm to the getPossibleMoves(), but it dosent store information about locations.
     * And when it finds a direction it can flip in it stores the direction in a list which it returns.
     *
     * @param loc - the location that is chosen by the player
     * @return a list of direction the player must flip
     */
    private List<GridDirection> flipperDirection(Location loc) {
        List<GridDirection> flippingDirections = new ArrayList<>();
        for (GridDirection someDirections : GridDirection.EIGHT_DIRECTIONS) {
            Location movedLocation = loc;
            boolean firstIteration = true;
            while (true) {
                movedLocation = movedLocation.getNeighbor(someDirections);
                if (!board.isOnGrid(movedLocation)) {
                    break;
                } else if (board.get(movedLocation) == null) {
                    break;
                } else if (board.get(movedLocation) == getCurrentPlayer()) {
                    if (!firstIteration) {
                        flippingDirections.add(someDirections);
                        break;
                    }
                } else {
                    firstIteration = false;
                }
            }
        }
        return flippingDirections;
    }

    /**
     * @param loc - from the location chosen flips the discs to the enemy in the directions in the list from the method above
     */
    private void flipper(Location loc) {
        for (GridDirection direction : flipperDirection(loc)) {
            Location nextFlip = loc;
            while (true) {
                nextFlip = nextFlip.getNeighbor(direction);
                if (!board.isOnGrid(nextFlip)) {
                    break;
                }
                if (board.get(nextFlip) == getCurrentPlayer()) {
                    break;
                } else {
                    board.flipAction(nextFlip, getCurrentPlayer());
                }
            }
        }
    }

    @Override
    public Game copy() {
        Othello game = new Othello(graphics);
        copyTo(game);
        return game;
    }

    /**
     * this boolean keeps track of if the last move where skipped by the player before.
     */
    private boolean lastMoveSkip = false;

    /**
     * Updates the lastMoveSkip boolean and keeps track of when the game should and and if any of tha player cna make their move.
     *
     * @return returns a boolean if the two last moves where skipped
     */
    private boolean noMoreMoves() {
        if (lastMoveSkip) {
            if (getPossibleMoves().isEmpty()) {
                return true;
            } else {
                lastMoveSkip = false;
                return false;
            }
        } else {
            if (getPossibleMoves().isEmpty()) {
                lastMoveSkip = true;
            }
        }
        return false;
    }

    /**
     * @param player The player that gets his discs counted
     * @return returns and integer of how many dics the player has on the board
     */
    private int points(Player player) {
        int counter = 0;
        for (Location loc : board.locations()) {
            if (board.get(loc) == player) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * This method checks which of the players wins the game
     *
     * @param player The player that is getting his discs compeard with all the other players
     * @return returns if the player of the input is the winner or not
     */
    private boolean pointChecker(Player player) {
        int mostPoints = 0;
        Player winningPlayer = null;
        for (Player p : players) {
            int playerPoints = points(p);
            if (playerPoints > mostPoints) {
                mostPoints = playerPoints;
                winningPlayer = p;
            } else if (playerPoints == mostPoints) {
                return false;
            }
        }
        return winningPlayer == player && winningPlayer != null;
    }

    @Override
    public boolean isWinner(Player player) {
        return pointChecker(player);
    }

    @Override
    public boolean gameOver() {
        if (!samePlayer) {
            if (noMoreMoves()) {
                return true;
            }
        }
        return board.isFull();
    }

    @Override
    public void restart() {
        super.restart();
        startingBoard();
        lastMoveSkip = false;
    }

    @Override
    public String getName() {
        return "Othello";
    }
}
