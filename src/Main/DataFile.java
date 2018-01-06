/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Pieces.Piece;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author leocakes
 */
public class DataFile {
    
    public void saveGame(Piece[][] boardArray, String filename){
        File file = new File("data/" + filename + ".chs");
        FileWriter writer;
        try{
            writer = new FileWriter(file, false);  
            for (int i = 0; i < boardArray.length; i++){
                for (int j = 0; j < boardArray[i].length; j ++){
                    writer.write(boardArray[i][j].print());
                }
            }
            System.out.println("Game has been saved under " + filename + ".chs");
            writer.close();
        } catch (IOException ex){
            System.out.println("Something went wrong with the file");
        }
    } //not tested
    
}
