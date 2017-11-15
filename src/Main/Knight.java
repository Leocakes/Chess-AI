/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.*;

/**
 *
 * @author brock
 */
public class Knight extends Piece {
    public Knight (int x, int y,Boolean side, Board board) {
        super(side,board);
    }
    public List<Integer[]> Collect() {
        List<Integer[]> list = new LinkedList<Integer[]>();
        return list;
    }
    public void run() {
        System.out.println("Run");
    }
}
