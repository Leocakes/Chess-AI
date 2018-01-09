package Main;

import Main.Pieces.Piece;
import java.awt.Point;

/**
 *
 * @author brock
 */
public class Move {
    public Piece piece;
    public Point move; //relative position
    public Point delete; //relative position
    
    public Move(Piece piece, Point move, Point delete) {
        this.piece = piece;
        this.move = move;
        this.delete = delete;
    }
    public Move(Piece piece, Point move) {
        this(piece,move,move);
    }
    
    public Move(Piece piece, int x, int y) {
        this(piece,new Point(x,y));
    }
    public Move(Piece piece, int x1, int y1, int x2, int y2) {
        this(piece, new Point(x1,x2), new Point(x2,y2));
    }
    public Point absolutePoint() {
        int x = piece.pos.x+move.x;
        int y = piece.pos.y+move.y;
        return new Point(x,y);
    }
}
