package Main;

import Main.Move;
import Main.Pieces.*;
import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 *
 * @author brock & dana
 */
public class Board implements Cloneable {

    public Piece[][] boardArray;

    public List<Piece> aliveList;

    public Stack<Revert> revertStack;

    public Board(String file) {
        aliveList = new LinkedList<Piece>();
        boardArray = new Piece[8][8];
        revertStack = new Stack();
        Reader reader = null;
        try {
            InputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in));
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
        } catch (Exception e) {
            System.out.println("File not found");
        }
        aliveList.removeAll(Collections.singleton(null)); //Removes all nulls from list
    } // reads board file and saves it onto the board

    public Double heuristic() { //higher = better for white    
        Double score = 0.0;
        for (Piece p : aliveList) {
            if (p.side == Side.White) {
                score += p.points;
            } else {
                score -= p.points;
            }
        }
        return score;
    } // calculates the heuristic score for the current board configuration

    public void doMove(Move move) {
        int mod = move.piece.side == Side.Black ? -1 : 1;
        int origx = move.piece.pos.x;
        int origy = move.piece.pos.y;
        int newx = move.move.x + origx;
        int newy = (mod * move.move.y) + origy;
        int delx = move.delete.x + origx;
        int dely = (mod * move.delete.y) + origy;
        if (boardArray[delx][dely] instanceof King && boardArray[delx][dely].side!=move.piece.side) {
            Piece store = boardArray[delx][dely];
            Piece king = boardArray[delx][dely];
            boardArray[delx][dely] = null;
            aliveList.remove(store);
            List<Move> removeList = new LinkedList();
            king.run();
            List<Move> kMoves = king.moves;
            for (Move m : fetchMoves(king.side == Side.White ? Side.Black : Side.White)) {
                for (Move mK : kMoves) {
                    if (m.absolutePoint().equals(mK.absolutePoint())) {
                        removeList.add(mK);
                    }
                }
            }
            kMoves.removeAll(removeList);
            boardArray[delx][dely] = store;
            aliveList.add(store);
            if (kMoves.isEmpty()) {
                
            } else {
                revertStack.add(null);
                return;
            }
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
    } // does the actual move

    public void revertMove() {
        Revert rev = revertStack.pop();
        if (rev != null) {
            rev.piece.pos = rev.piecePos;
            if (rev.del != null) {
                rev.del.pos = rev.delPos;
                aliveList.add(rev.del);
            }
            boardArray[rev.piecePos.x][rev.piecePos.y] = rev.piece;
            boardArray[rev.newPos.x][rev.newPos.y] = null;
            boardArray[rev.delPos.x][rev.delPos.y] = rev.del;
        }
    } // reverts the move

    public List<Move> fetchMoves(Side side) {
        List<Move> result = new LinkedList();
        for (Piece p : aliveList) {
            if (p.side == side) {
                p.run();
                result.addAll(p.moves);
            }
        }
        return result;
    } // gets the list of moves

    public void Print() {
        for (int y = 7; y >= 0; y--) {
            System.out.print(y + 1);
            for (int x = 0; x < 8; x++) {
                if (boardArray[x][y] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(boardArray[x][y].print());
                }
            }
            System.out.println();
        }
        System.out.println(" ABCDEFGH");
    } // prints the board

    public void saveGame(String filename) {
        File file = new File("data/" + filename);
        FileWriter writer;
        try {
            writer = new FileWriter(file, false);
            for (int i = 0; i < boardArray.length; i++) {
                for (int j = 0; j < boardArray[i].length; j++) {
                    if (boardArray[j][i] != null){
                        writer.write(boardArray[j][i].print()); // piece
                    } else{
                        writer.write("_");
                    } // if theres no piece on that spot 
                }
                writer.write("\n");
            }
            System.out.println("Game has been saved under " + filename);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong with the file");
        }
    } //saves the game

}
