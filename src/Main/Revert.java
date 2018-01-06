package Main;

import Main.Pieces.Piece;
import java.awt.Point;

/**
 *
 * @author brock
 */
public class Revert {
    public Point piecePos;
    public Piece piece;
    public Point newPos;
    public Point delPos;
    public Piece del;
    
    public Revert(Point piecePos,Piece piece,Point delPos,Piece del,Point newPos) {
        this.piecePos = piecePos;
        this.piece = piece;
        this.delPos = delPos;
        this.del = del;
        this.newPos = newPos;
    }
}
