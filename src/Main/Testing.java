package Main;

import java.awt.Point;

/**
 *
 * @author brock
 */
public class Testing {
    public Testing() {
        System.out.println("Hello");
    }
    public static void main(String[] args) {
        Board b = new Board("data/test1.chs");
        b.Print();
        b.aliveList.get(4).run();
        for (Move m : b.aliveList.get(4).moves) {
            Board newB = b.copy();
            newB.doMove(m);
            newB.Print();
        }
        
    }
}
