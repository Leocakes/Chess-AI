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
public class Move {
    public Piece piece;
    public Point move; //relative position
    public Point delete; //relative position
    
    public Move(Piece piece, Point move, Point delete) {
        this.piece = piece;
        this.move = move;
        this.delete = delete;
    }
    public Move(Piece piece, Point move) {
        this(piece,move,move);
    }
    
    public Move(Piece piece, int x, int y) {
        this(piece,new Point(x,y));
    }
    public Move(Piece piece, int x1, int y1, int x2, int y2) {
        this(piece, new Point(x1,x2), new Point(x2,y2));
    }
}
