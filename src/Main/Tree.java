package Main;

import Main.Pieces.Side;
import java.util.*;

/**
 *
 * @author brock
 */
public class Tree {

    List<Tree> children;
    Double alpha;
    Double beta;
    int depth;
    Move move;
    Double v;
    public static int maxDepth = 3;

    public Tree(Board board) {
        this(board, null, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
    
    public Move getNext() {
        Double highscore = Double.NEGATIVE_INFINITY;
        Move m = null;
        for(Tree t : children) {
            if (t.v>highscore) {
                highscore = t.v;
                m = t.move;
            }
        }
        return m;
    }

    public Tree(Board board, Move move, int depth, Double alpha, Double beta) {
        this.alpha = alpha;
        this.beta = beta;
        this.move = move;
        this.depth = depth;
        if (move != null) {
            board.doMove(move);
            board.Print();
        }
        children = new LinkedList();
        Boolean max = depth % 2 == 1;
        this.v = max ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        if (depth >= maxDepth) {
            this.v = board.heuristic();
        } else {
            for (Move m : board.fetchMoves(max ? Side.White : Side.Black)) {
                Tree t = new Tree(board, m, depth+1, alpha, beta);
                children.add(t);
                if (!max ^ v < t.v) {
                    this.v = t.v;
                }
            }
        }
        if (move != null) {
            board.revertMove();
        }
    }

}
