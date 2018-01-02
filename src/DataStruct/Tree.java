package DataStruct;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author brock
 */
public class Tree<T> {

    public T el;
    public List<Tree> children;
    public int depth;

    public Tree(T el) {
        this(el,1);
    }

    Tree(T el, int depth) {
        this.el = el;
        this.depth = depth;
        children = new LinkedList<Tree>();
    }

    public void addChild(T el) {
        children.add(new Tree(el,depth++));
    }
}
