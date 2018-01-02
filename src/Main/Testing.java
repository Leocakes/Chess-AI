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
        System.out.println(b.pieceList.size());
        b.pieceList.get(4).run();
        for (Move m : b.pieceList.get(4).moves) {
            System.out.println(m.move);
        }
        
    }
}
