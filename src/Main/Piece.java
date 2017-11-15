package Main;

import java.util.*;

/**
 *
 * @author brock
 */
public abstract class Piece implements Runnable {
    private Boolean side; //Which side of the board is the piece on
    private Board board;
    
    public Piece(Boolean side, Board board) {
        this.side=side;
        this.board=board;
    }
    
    Boolean isValid(int x, int y) { //Checks if a certain move is valid
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        } else if(board.boardArray[x][y]==null || board.boardArray[x][y].side==this.side) {
            return true;
        } else {
            return false;
        }
    }
    
    public abstract List<Integer[]> Collect(); //Returns a list of coordinates the piece can go to
}
