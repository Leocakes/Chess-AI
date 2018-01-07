package Main;

import Main.Pieces.Side;

/**
 *
 * @author brock
 */
public class Run {

    public Run() {
        Board b = new Board("data/board.chs");
        Boolean max = false;
        while(b.aliveList.size()>1) {
            Tree t = new Tree(b,max);
            b.doMove(t.getNext());
            b.Print();
            
            max = !max;
        }
    }

    public static void main(String[] args) {
        Run r = new Run();
    }
}
