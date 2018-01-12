package Main;

import Main.Pieces.Side;
import java.util.*;

/**
 *
 * @author brock & dana
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
        Double highscore = max?Double.NEGATIVE_INFINITY:Double.POSITIVE_INFINITY;
        Move m = null;
        for(Tree t : children) {
            if (max & t.v>=highscore) {
                highscore = t.v;
                m = t.move;
            } else if (!max & t.v<=highscore) {
                highscore = t.v;
                m = t.move;
            }
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
                Tree t = new Tree(board, m, depth+1, max?v:alpha, !max?v:beta,!max);
                children.add(t);
                if (max & v < t.v) { //Check if we can skip children
                    this.v = t.v;
                } else if (!max & v > t.v) {
                    this.v = t.v;
                }
                if (max & this.v > beta) { //Check if we can skip children
                    break;
                } else if (!max & this.v < alpha) {
                    break;
                }
            }
        }
        if (move != null) {
            board.revertMove();
        }
    }

}
