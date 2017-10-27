package Main;

import java.io.*;

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

    Piece[][] boardArray;

    public Board(String file) {
        boardArray = new Piece[8][8];
        Reader reader = null;
        try {
            InputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int x = 0; x < 8;x++) {
            for (int y = 0; y < 8;) {
                int c = -1;
                try {
                    c = reader.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (c == 'P' || c == 'p') {
                    boardArray[x][y] = new Pawn(x, y, c == 'P', this);
                } else if (c == 'Q' || c == 'q') {
                    boardArray[x][y] = new Queen(x, y, c == 'Q', this);
                } else if (c == 'K' || c == 'k') {
                    boardArray[x][y] = new King(x, y, c == 'K', this);
                } else if (c == 'R' || c == 'r') {
                    boardArray[x][y] = new Rook(x, y, c == 'R', this);
                } else if (c == 'B' || c == 'b') {
                    boardArray[x][y] = new Bishop(x, y, c == 'B', this);
                } else if (c=='N' || c == 'n') {
                    boardArray[x][y] = new Knight(x, y, c == 'N', this);
                } else if (c=='_') {
                    y++;
                }
                if (y<8 && boardArray[x][y]!=null) {
                    y++;
                }
            }
        }
    }
}
