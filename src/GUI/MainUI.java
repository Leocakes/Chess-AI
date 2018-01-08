package GUI;

import Main.Board;
import Main.Move;
import Main.Pieces.Piece;
import Main.Tree;
import java.awt.Point;
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
        Board board = null;
        while (running){
            System.out.println("Please provide us a command:");
            String [] args = scan.nextLine().split(" ");
            switch (args[0]){
                case "help": //shows help
                    helpUI();
                    break;
                case "difficulty": //changes difficulty
                    int difficulty = Integer.parseInt(args[1]);
                    // add call to change difficulty
                    break;
                case "new": //starts a new game
                    board = new Board("data/board.chs");
                    board.Print();
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
                        int y = Integer.parseInt(oldpos[0]) - 1;
                        int x = getPosition(oldpos[1]);
                        Piece currentPiece = board.boardArray[x][y];
                        String []newpos = args[2].split("");
                        Move m = currentPiece.getMove(new Point(getPosition(newpos[1]), Integer.parseInt(newpos[0]) - 1));
                        if (m != null){
                            board.doMove(m);
                            board.Print();
                            Tree tree = new Tree(board,false);
                            board.doMove(tree.getNext());
                            board.Print();
                        } else {
                            System.out.println(args[2] + " is not a valid move. Please try again.");
                        }
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
                    break;
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
        System.out.println("move oldposition newpostion  where oldposition is the position of the current piece you want to move and newposition is where the piece will be moved to. Example, move 2a 3a");
        System.out.println("exit                         exits the game");
    }
    
    //dana does ascii ui shiat    
}
