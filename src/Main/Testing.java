/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        System.out.println(b.pieceList.size());
        b.pieceList.get(0).run();
        System.out.println(b.pieceList.get(0).moves);
    }
}
