package Main.Pieces;

import Main.Board;
import Main.Move;
import Main.Pieces.Piece;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author brock
 */
public class Pawn extends Piece {

    Boolean en_passent; //True if it's possible to be taken by en passent

    public Pawn(int x, int y, Side side, Board board) {
        super(new Point(x, y), side, board);
        en_passent = false;
        points = 10.0;
    }

    public void run() {
        List<Move> possibleMoves = new LinkedList<Move>();
        Boolean moved = true;
        if ((side==Side.Black && pos.y==6) || (side==Side.White && pos.y==1)) {
            moved = false;
        }
        try {
        if (this.getPiece(new Point(0, 1)) == null) {
            possibleMoves.add(new Move(this, new Point(0, 1)));
        } 
        } catch(IndexOutOfBoundsException e) {
            
        }
        try {
            if (!moved & this.getPiece(new Point(0, 2)) == null) {
                possibleMoves.add(new Move(this, new Point(0, 2)));
            }
        } catch (IndexOutOfBoundsException e) {

        }
        
        try {
        Piece p = this.getPiece(new Point(1, 1));
        
        if (p!=null && p.side != this.side) {
            possibleMoves.add(new Move(this, new Point(1, 1)));
        } } catch(IndexOutOfBoundsException e) {
            
        }
        
        try {
        Piece p = this.getPiece(new Point(-1,1));
        
        if (p != null && p.side != this.side) {
            possibleMoves.add(new Move(this, new Point(-1, 1)));
        }
        } catch(IndexOutOfBoundsException e) {
            
        }

        moved = true;
        moves = possibleMoves;
    }

    public String print() {
        return side.equals(Side.White) ? "P" : "p";
    }
}
