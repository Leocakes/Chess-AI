package Main;

import DataStruct.Tree;
import java.util.List;

/**
 *
 * @author brock
 */
public class NextMove {
    public static int maxDepth = 3;
    public Tree<Board> root;
    
    public NextMove(Board board) {
        this.root = new Tree(board);
    }
    
    void genTree(Tree<Board> tree) {
        if(tree.depth >= maxDepth) {
            tree.el.heuristic();
        } else {

        }
    }
    
}
