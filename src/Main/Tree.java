package Main;

import java.util.*;

/**
 *
 * @author brock
 */
public class Tree {
    Tree parent;
    List<Tree> children;
    Double alpha;
    Double beta;
    int depth;
    static Board board;
    Queue<Move> moveQueue;
    
    public Tree(Board board) {
        Tree.board = board;
        alpha = Double.NEGATIVE_INFINITY;
        beta = Double.POSITIVE_INFINITY;
        depth = 1;
        children = new LinkedList();
        moveQueue = new LinkedList();
    }
    
}
