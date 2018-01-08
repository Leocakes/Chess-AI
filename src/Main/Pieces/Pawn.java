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
        if ((side == Side.Black && pos.y == 6) || (side == Side.White && pos.y == 1)) {
            moved = false;
        }
        Piece curP = this.getPiece(new Point(0, 1));
        if (curP == null && !(curP instanceof EmptyPiece)) {
            possibleMoves.add(new Move(this, new Point(0, 1)));
        }
        curP = this.getPiece(new Point(0, 2));
        if (!moved && curP == null && !(curP instanceof EmptyPiece)) {
            possibleMoves.add(new Move(this, new Point(0, 2)));
        }

            Piece p = this.getPiece(new Point(1, 1));

            if (p != null && p.side != this.side && !(p instanceof EmptyPiece)) {
                possibleMoves.add(new Move(this, new Point(1, 1)));
            }

            p = this.getPiece(new Point(-1, 1));
            if (p != null && p.side != this.side && !(p instanceof EmptyPiece)) {
                possibleMoves.add(new Move(this, new Point(-1, 1)));
            }


        moved = true;
        moves = possibleMoves;
    }

    public String print() {
        return side.equals(Side.White) ? "P" : "p";
    }
}
