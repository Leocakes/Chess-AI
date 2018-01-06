package Main.Pieces;

import Main.Board;
import Main.Move;
import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Piece implements Runnable {

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

    Piece getPiece(Point p) {
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
            throw new IndexOutOfBoundsException();
        }
        return board.boardArray[x][y];
    }

    List<Move> filterPositions(List<Move> positions) {
        return positions.stream().filter(m -> isValid(m.move)).collect(Collectors.toList());
    }

    Boolean isValid(Point p) { //Checks if a certain move is valid
        try {
            Piece piece = getPiece(p);
            if (piece == null || piece.side != this.side) {
                return true;
            } else {
                return false;
            }
        } catch (Exception IndexOutOfBoundsException) {
            return false;
        }
    }

    List<Move> checkLines(List<Point> increment) {
        List<Move> possibleMoves = new LinkedList<Move>();
        for (Point p : increment) {
            for (int i = 1; i < 8; i++) {
                Point nextP = new Point((p.x * i), (p.y * i));
                try {
                    Piece piece = getPiece(nextP);
                    if (piece==null) {
                        
                        possibleMoves.add(new Move(this, nextP));
                    }
                    else if (!piece.side.equals(this.side)) {
                        possibleMoves.add(new Move(this, nextP));
                        break;
                    } else {
                        break;
                    }
                } catch (Exception IndexOutOfBoundsException) {
                    break;
                }

            }

        }
        return possibleMoves;
    }

    abstract public String print();

    abstract public void run();
}
