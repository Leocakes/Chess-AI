package Main;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.text.Position;

/**
 *
 * @author brock
 */
public abstract class Piece implements Runnable {
    private Side side; //Which side of the board is the piece on
    private Board board;
    public Point currentPosition;
    public List<Point> moves; //after you call run this value will have the next possible moves
    //Note, the responsibity for checking if a move is possible is on the piece not board
    
    public Piece(Point p,Side side, Board board) {
        this.currentPosition = p;
        this.side=side;
        this.board=board;
    }
    
    List<Point> filterPositions(List<Point> positions) {
        return positions.stream().filter(x -> isValid(x)).collect(Collectors.toList()); 
    }
    
    private Boolean isValid(Point p) { //Checks if a certain move is valid
        int x = p.x;
        int y = p.y;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        } else if(board.getPiece(new Point(x,y),this.side)==null || board.getPiece(new Point(x,y), this.side).side==this.side) {
            return true;
        } else {
            return false;
        }
    }
    
    abstract public void run();
}
