package GUI;

import Main.Board;
import Main.Move;
import Main.Pieces.Piece;
import Main.Pieces.Side;
import Main.Tree;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author Dana Darmohray & Brock Jones
 */
public class MainUI {

    public MainUI() {
        System.out.println("Welcome to the game of Chess!");
        System.out.println("Play aganist an ai. GLHF :)");
        helpUI();
        Game();
    } //starts the user interface

    public void Game() {
        int depth = 4;
        Boolean running = true;
        Scanner scan = new Scanner(System.in);
        Board board = null;
        while (running) {
            System.out.println("Please provide us a command:");
            String[] args = scan.nextLine().split(" ");
            switch (args[0]) {
                case "help": //shows help
                    helpUI();
                    break;
                case "difficulty": //changes difficulty
                    try {
                        int difficulty = Integer.parseInt(args[1]);
                        if (difficulty < 6 && difficulty > 2) {
                            Tree.maxDepth = difficulty;
                        } else {
                            System.out.println("Please provide a number from 3-5.");
                        }
                    } catch (Exception e) {
                        System.out.println("Please provide an integer for the difficulty.");
                    }
                    break;
                case "new": //starts a new game
                    board = new Board("data/board.chs");
                    board.Print();
                    break;
                case "load": //loads a saved game
                    board = new Board("data/" + args[1]); // loads 
                    System.out.println();
                    board.Print();
                    break;
                case "save":
                    if (board != null) {
                        board.saveGame(args[1]);
                    } else {
                        System.out.println("Cannot save a non-existing game.");
                    }
                    break;
                case "move":
                    if (board != null) {
                        try {
                            String[] oldpos = args[1].split("");
                            int y = Integer.parseInt(oldpos[1]) - 1;
                            int x = getPosition(oldpos[0]);
                            Piece currentPiece = board.boardArray[x][y];
                            if (currentPiece != null) {
                                String[] newpos = args[2].split("");
                                Move m = currentPiece.getMove(new Point(getPosition(newpos[0]), Integer.parseInt(newpos[1]) - 1));
                                if (m != null) {
                                    board.doMove(m);
                                    System.out.println();
                                    board.Print();
                                    System.out.println();
                                    Tree tree = new Tree(board, false);
                                    board.doMove(tree.getNext());
                                    board.Print();
                                    System.out.println();
                                } else {
                                    System.out.println(args[2] + " is not a valid move. Please try again.");
                                }
                            } else {
                                System.out.println("Not a piece");
                            }
                            //Check for Checkmate
                            List<Move> moves = board.fetchMoves(Side.White);
                            for (Move m : moves) {
                                if (m.piece.getPiece(m.move) instanceof Main.Pieces.King) {
                                    board.doMove(m);
                                }
                            }
                            //Check if anyone has won
                            int kingCount = 0;
                            for (Piece p : board.aliveList) {
                                if(p instanceof Main.Pieces.King) {
                                    kingCount++;
                                }
                            }
                            if (kingCount<2) {
                                System.out.println("CheckMate!");
                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println("Please provide proper parameters. \nExample, move a2 a3");
                        }
                    } else {
                        System.out.println("Please start a game.");
                    }
                    break;
                case "exit":
                    System.out.println("Are you sure you wish to exit? (y for yes)");
                    if (scan.next().equals("y")) {
                        System.out.println("Thanks for playing!");
                        running = false;
                    } // exiting
                    break;
                default:
                    System.out.println("Please provide a proper command.");
                    System.out.println("Use the help command for more information.");
                    break; //if bad command
            }
            Runtime run = Runtime.getRuntime();
            run.gc();
        }
    } // play of the game

    public int getPosition(String letter) {
        switch (letter.toLowerCase()) {
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
        } // converts letter to number for the array
        System.out.println("Please provide a proper position.");
        return 10;
    } // converter

    public void helpUI() {
        System.out.println("help                         shows the help page");
        System.out.println("difficulty x                 set the difficulty, where x is the difficulty level (3-5), default 4");
        System.out.println("new game                     starts a new game");
        System.out.println("load filename                load a game file");
        System.out.println("save filename                save the current game to a file");
        System.out.println("move oldposition newpostion  where oldposition is the position of the current piece you want to move and newposition is where the piece will be moved to. Example, move a2 a3");
        System.out.println("exit                         exits the game");
    } // help 

}
