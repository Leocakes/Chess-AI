package Main;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author brock
 */
public class Rook extends Piece {

    public Rook(int x, int y, Side side, Board board) {
        super(new Point(x, y), side, board);
    }

    public List<Point> Collect() {
        List<Point> list = new LinkedList<Point>();

        return list;
    }

    public void run() {
        List<Move> possibleMoves = new LinkedList<Move>();
        Point[] increment = new Point[]{new Point(0, 1),
            new Point(1, 0), new Point(0, -1),
            new Point(-1, 0)};
        List<Point> incrementList = new LinkedList(Arrays.asList(increment));
        moves = checkLines(incrementList);
    }

    public String print() {
        return side.equals(Side.White) ? "R" : "r";
    }
}
