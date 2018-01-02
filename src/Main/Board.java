package Main;

import Main.Move;
import Main.Pieces.*;
import java.awt.Point;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author brock
 */
public class Board {

    public Piece[][] boardArray;
    
    public List<Piece> pieceList;
    
    public Double score;

    public Board(String file) {
        pieceList = new LinkedList<Piece>();
        boardArray = new Piece[8][8];
        Reader reader = null;
        try {
            InputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8;) {
                int c = -1;
                try {
                    c = reader.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (c != '_') {
                    if (c == 'P' || c == 'p') {
                        boardArray[x][y] = new Pawn(x, y, c == 'P' ? Side.White : Side.Black, this);
                    } else if (c == 'Q' || c == 'q') {
                        boardArray[x][y] = new Queen(x, y, c == 'Q' ? Side.White : Side.Black, this);
                    } else if (c == 'K' || c == 'k') {
                        boardArray[x][y] = new King(x, y, c == 'K' ? Side.White : Side.Black, this);
                    } else if (c == 'R' || c == 'r') {
                        boardArray[x][y] = new Rook(x, y, c == 'R' ? Side.White : Side.Black, this);
                    } else if (c == 'B' || c == 'b') {
                        boardArray[x][y] = new Bishop(x, y, c == 'B' ? Side.White : Side.Black, this);
                    } else if (c == 'N' || c == 'n') {
                        boardArray[x][y] = new Knight(x, y, c == 'N' ? Side.White : Side.Black, this);
                    }
                    pieceList.add(boardArray[x][y]);
                } else if(c == '_') {
                    x++;
                }
                if (x < 8 && boardArray[x][y] != null) {
                    x++;
                }
            }
        }
        pieceList.removeAll(Collections.singleton(null)); //Removes all nulls from list
    }
    
    public void heuristic() { //Calculates a score for this board, higher = better for white
        Double score = 0.0;
        for(Piece p : pieceList) {
            if(p.side.equals(Side.White)) {
                score++;
            } else {
                score--;
            }
        }
        this.score = score;
    }
    
    public void doMove(Move move) {
        int origx = move.piece.pos.x;
        int origy = move.piece.pos.y;
        int newx = move.move.x;
        int newy = move.move.y;
        int delx = move.delete.x;
        int dely = move.delete.y;
        if(boardArray[delx][dely]!=null) {
            pieceList.remove(boardArray[delx][dely]);
        }
        boardArray[delx][dely]=null;
        boardArray[newx][newy]=boardArray[origx][origy];
        
    }
    
    public void Print() {
        for (int y=0;y<8;y++) {
            for (int x=0;x<8;x++) {
                if (boardArray[x][y]==null) {
                    System.out.print(".");
                } else {
                    System.out.print(boardArray[x][y].print());
                }
            }
            System.out.println();
        }
    }
}
