package Main.Pieces;

import Main.Board;
import Main.Pieces.Piece;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author brock
 */
public class Queen extends Piece {
    public Double points = 900.0;

    public Queen(int x, int y, Side side, Board board) {
        super(new Point(x, y), side, board);
    }

    public List<Point> Collect() {
        List<Point> list = new LinkedList<Point>();
        return list;
    }

    public void run() {
        Point[] increment = new Point[]{new Point(0, 1), new Point(1, 1),
            new Point(1, 0), new Point(1, -1), new Point(0, -1), new Point(-1, -1),
            new Point(-1, 0), new Point(-1, 1)};
        List<Point> incrementList = new LinkedList(Arrays.asList(increment));
        moves = checkLines(incrementList);
    }
    
        public String print() {
        return side.equals(Side.White)?"Q":"q";
    }
}
