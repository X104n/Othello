package inf101.sem2.game;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.Player;

import java.sql.SQLOutput;
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

    public Othello(Graphics graphics) {
        super(new GameBoard(8, 8), graphics);
    }

    public Othello(Graphics graphics, Player player1, Player player2) {
        super(new GameBoard(8, 8), graphics);
        addPlayer(player1);
        addPlayer(player2);
        startingBoard();
    }

    public Othello(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);
        startingBoard();
    }

    @Override
    public void makeMove(Location loc) {
        if (getPossibleMoves().isEmpty()) {
            System.out.println("you can not make a move, skipping your turn");
            players.nextPlayer();
            samePlayer = false;
        }
        else if (!canPlace(loc)) {
            throw new IllegalArgumentException("Can not make that move");
        }
        else if (locationInList(loc)) {
            board.set(loc, getCurrentPlayer());
            flipper(loc);
            players.nextPlayer();
            samePlayer = false;
        }
        else{
            System.out.println(getPossibleMoves());
            samePlayer = true;
        }

    }

    private boolean samePlayer = false;

    private boolean locationInList(Location loc) {
        List<Location> theList = getPossibleMoves();
        for (Location locationCheck : theList) {
            if (loc.equals(locationCheck)) {
                return true;
            }
        }
        return false;
    }

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
                            if(!possibleMoves.contains(startingLocations)){
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

    private boolean lastMoveSkip = false;

    private boolean noMoreMoves(){
        if(lastMoveSkip){
            if(getPossibleMoves().isEmpty()){
                return true;
            }
            else{
                lastMoveSkip = false;
                return false;
            }
        }
        else{
            if(getPossibleMoves().isEmpty()){
                lastMoveSkip = true;
            }
        }
        return false;
    }

    private int points (Player player){
        int counter = 0;
        for (Location loc : board.locations()){
            if(board.get(loc) == player){
                counter++;
            }
        }
        return counter;
    }

    private boolean pointChecker(Player player){
        int mostPoints = 0;
        Player winningPlayer = null;
        for (Player p : players){
            int playerPoints = points(p);
            if(playerPoints > mostPoints){
                mostPoints = playerPoints;
                winningPlayer = p;
            }
            else if(playerPoints == mostPoints){
                return false;
            }
        }
        if(winningPlayer == player && winningPlayer != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean isWinner(Player player) {
        if(pointChecker(player)){
            return true;
        }
        return false;
    }

    @Override
    public boolean gameOver() {
        if(!samePlayer){
            if(noMoreMoves()){
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
