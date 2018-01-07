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
    public static int maxDepth = 4;
    Boolean max;

    public Tree(Board board, Boolean max) {
        this(board, null, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,max);
    }
    
    public Move getNext() {
        Double highscore = Double.NEGATIVE_INFINITY;
        Move m = null;
        for(Tree t : children) {
            if (t.v>=highscore) {
                highscore = t.v;
                m = t.move;
            }
        }
        if (m==null) {
            System.out.println("rip");
        }
        return m;
    }

    public Tree(Board board, Move move, int depth, Double alpha, Double beta,Boolean max) {
        this.alpha = alpha;
        this.beta = beta;
        this.move = move;
        this.depth = depth;
        this.max = max;
        if (move != null) {
            board.doMove(move);
        }
        children = new LinkedList();
        this.v = max ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        if (depth >= maxDepth) {
            this.v = board.heuristic();
        } else {
            for (Move m : board.fetchMoves(max ? Side.White : Side.Black)) {
                Tree t = new Tree(board, m, depth+1, alpha, beta,!max);
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
