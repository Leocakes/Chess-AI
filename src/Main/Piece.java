package Main;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.text.Position;


public abstract class Piece implements Runnable {

    Side side; //Which side of the board is the piece on
    private Board board;
    public Point currentPosition;
    public List<Move> moves; //after you call run this value will have the next possible moves
    //Note, the responsibity for checking if a move is possible is on the piece not board

    public Piece(Point p, Side side, Board board) {
        this.currentPosition = p;
        this.side = side;
        this.board = board;
    }

    List<Move> filterPositions(List<Move> positions) {
        return positions.stream().filter(x -> isValid(x.move)).collect(Collectors.toList());
    }

    Boolean isValid(Point p) { //Checks if a certain move is valid
        int x = p.x;
        int y = p.y;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        } else if (board.getPiece(new Point(x, y), this.side) == null
                || board.getPiece(new Point(x, y), this.side).side == this.side) {
            return true;
        } else {
            return false;
        }
    }
    
    List<Move> checkLines(List<Point> increment) {
        List<Move> possibleMoves = new LinkedList<Move>();
        for (Point p : increment) {
            for (int i = 0; i < 8; i++) {
                Point nextP = new Point(p.x*i,p.y*i);
                if (isValid(nextP)) {
                    possibleMoves.add(new Move(this,nextP));
                } else {
                    break;
                }
            }
        
        }
        return possibleMoves;
    }
    
    abstract public String print();

    abstract public void run();
}
