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
    
    Boolean moved;
    Boolean en_passent; //True if it's possible to be taken by en passent
    
    public Pawn (int x, int y,Side side, Board board) {
        super(new Point(x,y),side,board);
        moved=false;
        en_passent = false;
    }
    
    public void run() {
        List<Move> possibleMoves = new LinkedList<Move>();
        possibleMoves.add(new Move(this,0,1));
        if (!moved) {
            possibleMoves.add(new Move(this,0,2));
        }
        moved = true;
        moves = this.filterPositions(possibleMoves);
    }
    
    public String print() {
        return side.equals(Side.White)?"P":"p";
    }
}
