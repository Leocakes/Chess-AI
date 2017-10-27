/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
        for (int x=0;x<8;x++) {
            for (int y=0;y<8;y++) {
                System.out.println(b.boardArray[x][y]);
            }
        }
    }
}
