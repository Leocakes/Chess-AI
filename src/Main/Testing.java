package Main;

import Main.Pieces.Side;
import java.awt.Point;

/**
 *
 * @author brock
 */
public class Testing {

    public Testing() {
        test3();
    }

    public static void main(String[] args) {
        Testing t = new Testing();

    }

    public void test1() {
        Board b = new Board("data/test1.chs");
        b.aliveList.get(4).run();
        for (Move m : b.aliveList.get(4).moves) {
            b.doMove(m);
            b.Print();
            System.out.println();
            b.revertMove();
            b.Print();
            System.out.println("--------");
        }
    }
    
    public void test2() {
        Board b = new Board("data/test3.chs");
        Tree t = new Tree(b,true);
        b.doMove(t.getNext());
        b.Print();
        
    }
    
    public void test3() {
        Board b = new Board("data/test4.chs");
        Tree t = new Tree(b,true);
        b.doMove(t.getNext());
        b.Print();
    }
}
