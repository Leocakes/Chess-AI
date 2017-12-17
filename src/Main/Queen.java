/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author brock
 */
public class Queen extends Piece {
    public Queen (int x, int y,Side side, Board board) {
        super(new Point(x,y),side,board);
    }
    public List<Point> Collect() {
        List<Point> list = new LinkedList<Point>();
        return list;
    }
    public void run() {
        System.out.println("Run");
    }
}
