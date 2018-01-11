package Main.Pieces;

import Main.Board;
import java.awt.Point;

/**
 *
 * @author brock & dana
 */

//If you try to fetch a piece that doesn't exist, this will get return
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
