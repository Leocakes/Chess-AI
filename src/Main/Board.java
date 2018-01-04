package Main;

import Main.Move;
import Main.Pieces.*;
import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 *
 * @author brock
 */
public class Board implements Cloneable {

    public Piece[][] boardArray;

    public List<Piece> aliveList;

    public Double score;

    public Stack<Move> moveStack;

    public Stack<Piece> deleteStack;

    public Board copy() {
        try {
            return (Board) this.clone();
        } catch (Exception CloneNotSupported) {
            return null;
        }
    }

    public Board(String file) {
        aliveList = new LinkedList<Piece>();
        boardArray = new Piece[8][8];
        moveStack = new Stack();
        deleteStack = new Stack();
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
                    aliveList.add(boardArray[x][y]);
                } else if (c == '_') {
                    x++;
                }
                if (x < 8 && boardArray[x][y] != null) {
                    x++;
                }
            }
        }
        aliveList.removeAll(Collections.singleton(null)); //Removes all nulls from list
    }

    public void heuristic() { //Calculates a score for this board, higher = better for white
        Double score = 0.0;
        for (Piece p : aliveList) {
            if (p.side.equals(Side.White)) {
                score++;
            } else {
                score--;
            }
        }
        this.score = score;
    }

    public void doMove(Move move) {
        moveStack.push(move);
        int origx = move.piece.pos.x;
        int origy = move.piece.pos.y;
        int newx = move.move.x + origx;
        int newy = move.move.y + origy;
        int delx = move.delete.x + origx;
        int dely = move.delete.y + origy;
        deleteStack.push(boardArray[delx][dely]);
        if (boardArray[delx][dely] != null) {
            aliveList.remove(boardArray[delx][dely]);
        }

        boardArray[delx][dely] = null;
        boardArray[newx][newy] = boardArray[origx][origy];
        boardArray[origx][origy] = null;
        boardArray[newx][newy].pos = new Point(newx,newy);
    }

    public void revertMove() {
        Move move = moveStack.pop();
        Piece del = deleteStack.pop();
        boardArray[del.pos.x][del.pos.y] = del;
        int x = move.piece.pos.x - move.move.x;
        int y = move.piece.pos.y - move.move.y;
        boardArray[x][y] = move.piece;
        move.piece.pos = new Point(x,y);
    }

    public void Print() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (boardArray[x][y] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(boardArray[x][y].print());
                }
            }
            System.out.println();
        }
    }
}
