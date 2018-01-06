package Main;

import Main.Move;
import Main.Pieces.*;
import java.awt.Point;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author brock
 */
public class Board implements Cloneable {

    public Piece[][] boardArray;

    public List<Piece> aliveList;

    public Double score;

    public Stack<Revert> revertStack;

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
        revertStack = new Stack();
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

    public Double heuristic() { //Calculates a score for this board, higher = better for white
        Double score = 0.0;
        for (Piece p : aliveList) {
            if (p.side.equals(Side.White)) {
                score++;
            } else {
                score--;
            }
        }
        return score;
    }

    public void doMove(Move move) {
        int mod = move.piece.side == Side.Black ? -1 : 1;
        int origx = move.piece.pos.x;
        int origy = move.piece.pos.y;
        int newx = move.move.x + origx;
        int newy = (mod * move.move.y) + origy;
        int delx = move.delete.x + origx;
        int dely = (mod * move.delete.y) + origy;
        if (delx > 7 | dely > 7 | delx < 0 | dely < 0) {
            System.out.println("Oops");
        }
        if (boardArray[delx][dely] != null) {
            aliveList.remove(boardArray[delx][dely]);
        }
        revertStack.add(new Revert(new Point(origx, origy),
                boardArray[origx][origy],
                new Point(delx, dely),
                boardArray[delx][dely],
                new Point(newx, newy)));
        boardArray[delx][dely] = null;
        boardArray[newx][newy] = boardArray[origx][origy];
        boardArray[origx][origy] = null;
        boardArray[newx][newy].pos = new Point(newx, newy);
    }

    public void revertMove() {
        Revert rev = revertStack.pop();
        rev.piece.pos = rev.piecePos;
        if (rev.del != null) {
            rev.del.pos = rev.delPos;
            aliveList.add(rev.del);
        }
        boardArray[rev.piecePos.x][rev.piecePos.y] = rev.piece;
        boardArray[rev.newPos.x][rev.newPos.y] = null;
        boardArray[rev.delPos.x][rev.delPos.y] = rev.del;
    }

    public List<Move> fetchMoves(Side side) {
        List<Move> result = new LinkedList();
        for (Piece p : aliveList) {
            if (p.side == side) {
                p.run();
                result.addAll(p.moves);
            }
        }
        return result;
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
        System.out.println();
    }
}
