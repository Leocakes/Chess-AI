/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author brock
 */
public class Queen extends Piece {

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
}
