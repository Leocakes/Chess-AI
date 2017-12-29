package Main;

import java.awt.Point;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brock
 */
public class Board {

    private Piece[][] boardArray;
    
    public List<Piece> pieceList;

    public Piece getPiece(Point p, Side side) {
        if (side.equals(Side.Black)) {
            return boardArray[p.x][7 - p.y];
        } else {
            return boardArray[p.x][p.y];
        }
    }

    public Board(String file) {
        pieceList = new LinkedList<Piece>();
        boardArray = new Piece[8][8];
        Reader reader = null;
        try {
            InputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8;) {
                int c = -1;
                try {
                    c = reader.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (c != '_') {
                    if (c == 'P' || c == 'p') {
                        boardArray[x][y] = new Pawn(x, y, c == 'P' ? Side.White : Side.Black, this);
                    } else if (c == 'Q' || c == 'q') {
                        boardArray[x][y] = new Queen(x, y, c == 'Q' ? Side.White : Side.Black, this);
                    } else if (c == 'K' || c == 'k') {
                        boardArray[x][y] = new King(x, y, c == 'K' ? Side.White : Side.Black, this);
                    } else if (c == 'R' || c == 'r') {
                        boardArray[x][y] = new Rook(x, y, c == 'R' ? Side.White : Side.Black, this);
                    } else if (c == 'B' || c == 'b') {
                        boardArray[x][y] = new Bishop(x, y, c == 'B' ? Side.White : Side.Black, this);
                    } else if (c == 'N' || c == 'n') {
                        boardArray[x][y] = new Knight(x, y, c == 'N' ? Side.White : Side.Black, this);
                    }
                    pieceList.add(boardArray[x][y]);
                } else if(c == '_') {
                    y++;
                }
                if (y < 8 && boardArray[x][y] != null) {
                    y++;
                }
            }
        }
        pieceList.removeAll(Collections.singleton(null)); //Removes all nulls from list
    }
    
    public void Print() {
        for (int x=0;x<8;x++) {
            for (int y=0;y<8;y++) {
                if (boardArray[x][y]==null) {
                    System.out.print(".");
                } else {
                    System.out.print(boardArray[x][y].print());
                }
            }
            System.out.println();
        }
    }
    
}
