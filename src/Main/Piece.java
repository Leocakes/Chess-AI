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
public abstract class Piece implements Runnable {
    private int x;
    private int y;
    private Boolean side; //Which side of the board is the piece on
    private Board board;
    
    public Piece(int x, int y, Boolean side, Board board) {
        this.x=x;
        this.y=y;
        this.side=side;
        this.board=board;
    }
    
    public abstract List<Integer[]> Collect(); //Returns a list of coordinates the piece can go to
}
