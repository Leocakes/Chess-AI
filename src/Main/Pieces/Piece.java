package Main.Pieces;

import Main.Board;
import Main.Move;
import java.awt.Point;
import java.util.*;

public abstract class Piece implements Runnable {
    public Double points;
    public Side side; //Which side of the board is the piece on
    private Board board;
    public Point pos;
    public List<Move> moves; //after you call run this value will have the next possible moves
    //Note, the responsibity for checking if a move is possible is on the piece not board
    public Boolean alive;

    public Piece(Point p, Side side, Board board) {
        this.pos = p;
        this.side = side;
        this.board = board;
        alive = true;
    }
    
    public Move getMove(Point abs) {
        int x = abs.x - pos.x;
        int y = abs.y - (side==Side.Black?-1:1 * pos.y);
        this.run();
        for (Move m : moves) {
            if (m.move.x == x && m.move.y == y) {
                return m;
            }
        }
        return null;
    }

    public Piece getPiece(Point p) { //relative
        int x = 0;
        int y = 0;
        if (this.side.equals(Side.White)) {
            x = p.x + this.pos.x;
            y = p.y + this.pos.y;
        } else {
            x = p.x + this.pos.x;
            y = (-1 * p.y) + this.pos.y;
        }
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return new EmptyPiece();
        }
        return board.boardArray[x][y];
    }
    
    List<Move> filterPositions(List<Move> positions) {
        List<Move> result = new LinkedList();
        for(Move m : positions) {
            if (isValid(m.move)) {
                result.add(m);
            }
        }
        return result;
    }

    Boolean isValid(Point p) { //Checks if a certain move is valid
            Piece piece = getPiece(p);
            if (piece instanceof EmptyPiece) {
                return false;
            }
            if (piece == null || piece.side != this.side) {
                return true;
            } else {
                return false;
            }
    }

    List<Move> checkLines(List<Point> increment) {
        List<Move> possibleMoves = new LinkedList<Move>();
        for (Point p : increment) {
            for (int i = 1; i < 8; i++) {
                Point nextP = new Point((p.x * i), (p.y * i));

                    Piece piece = getPiece(nextP);
                    if (piece instanceof EmptyPiece) {
                        break;
                    }
                    if (piece==null) {
                        
                        possibleMoves.add(new Move(this, nextP));
                    }
                    else if (!piece.side.equals(this.side)) {
                        possibleMoves.add(new Move(this, nextP));
                        break;
                    } else {
                        break;
                    }
                

            }

        }
        return possibleMoves;
    }

    abstract public String print();

    abstract public void run();
}
