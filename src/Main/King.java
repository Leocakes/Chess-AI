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
public class King extends Piece {

    public King(int x, int y, Side side, Board board) {
        super(new Point(x, y), side, board);
    }

    public List<Point> Collect() {
        List<Point> list = new LinkedList<Point>();
        return list;
    }

    public void run() {
        List<Move> possibleMoves = new LinkedList<Move>();
        possibleMoves.add(new Move(this, new Point(1, 0)));
        possibleMoves.add(new Move(this, new Point(1, -1)));
        possibleMoves.add(new Move(this, new Point(0, -1)));
        possibleMoves.add(new Move(this, new Point(-1, -1)));
        possibleMoves.add(new Move(this, new Point(-1, 0)));
        possibleMoves.add(new Move(this, new Point(-1, 1)));
        possibleMoves.add(new Move(this, new Point(0, 1)));
        possibleMoves.add(new Move(this, new Point(1, 1)));
        moves = filterPositions(possibleMoves);
    }
    
        public String print() {
        return side.equals(Side.White)?"K":"k";
    }
}
