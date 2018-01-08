package Main.Pieces;

import Main.Board;
import java.awt.Point;

/**
 *
 * @author brock
 */
public class EmptyPiece extends Piece {
    
    public EmptyPiece() {
        super(null,null,null);
    }
    
    public void run() {
        
    }
    public String print() {
        return "_";
    }
}
