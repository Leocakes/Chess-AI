package GUI;

import Main.Board;
import Main.Move;
import Main.Pieces.Piece;
import java.util.Scanner;

/**
 *
 * @author leocakes
 */
public class MainUI {

    public MainUI(){
        System.out.println("Welcome to the game of Chess!");
        System.out.println("Play aganist an ai. GLHF :)");
        helpUI();
        Game();
    } //blank
    
     public void Game(){
        Boolean running = true;
        Scanner scan = new Scanner(System.in);
        String input;
        
        while (running){
            System.out.println("Please provide us a command:");
            input = scan.next();
            String [] args = input.split(" ");
            Board board = null;
            switch (args[0]){
                case "help": //shows help
                    helpUI();
                    break;
                case "difficulty": //changes difficulty
                    int difficulty = Integer.parseInt(args[1]);
                    // add call to change difficulty
                    break;
                case "new": //starts a new game
                    board = new Board("board.chs");
                    break;
                case "load":
                    board = new Board(args[1]);
                    // loads 
                    break;
                case "save":
                    if (board != null){
                        board.saveGame(args[1] + ".chs");
                    } else {
                        System.out.println("Cannot save a non-existing game.");
                    }
                    break;
                case "move":
                    if (board != null){
                        String []oldpos = args[1].split("");
                        Piece currentPiece = board.boardArray[Integer.getInteger(oldpos[0])][getPosition(oldpos[1])];
                        String []newpos = args[2].split("");
                        currentPiece.run();
                        //todo: call function to activate moves
                    } else {
                        System.out.println("Please start a game.");
                    }
                    break;
                case "exit":
                    System.out.println("Are you sure you wish to exit? (y for yes)");
                    if (scan.next().equals("y")){
                        System.out.println("Thanks for playing!");
                        running = false;
                    }
                    break;
                default:
                    System.out.println("Please provide a proper command.");
                    System.out.println("Use the help command for more information.");
            }
        }
        
    
    }
     
    public int getPosition(String letter){
        switch(letter){
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
        }
        System.out.println("Please provide a proper position.");
        return 10;
    }
    
    public void helpUI(){
        System.out.println("help                         shows the help page");
        System.out.println("difficulty x                 set the difficulty, where x is the difficulty level (3-7), default 5");
        System.out.println("new game                     starts a new game");
        System.out.println("load filename                load a game file");
        System.out.println("save filename                save the current game to a file");
        System.out.println("move oldposition newpostion  where oldposition is the position of the current piece you want to move and newposition is where the piece will be moved to. Example, move a2 a3");
        System.out.println("exit                         exits the game");
    }
    
    //dana does ascii ui shiat    
}
