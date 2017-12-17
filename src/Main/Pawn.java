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
public class Pawn extends Piece {
    
    public Pawn (int x, int y,Side side, Board board) {
        super(new Point(x,y),side,board);
    }
    
    public void run() {
        List<Point> possibleMoves = new LinkedList<Point>();
        possibleMoves.add(new Point(0,1));
        if (currentPosition.y!=1) {
            possibleMoves.add(new Point(0,2));
        }
        moves = this.filterPositions(possibleMoves);
    }
}
