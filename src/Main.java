import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;



public class Main {



    public static void log(String v)
    {
        System.out.println(v);
    }

    // This function returns true if there are moves
// remaining on the board. It returns false if
// there are no moves left to play.










    public static void main(String[] args) {

        Random r = new Random();
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);  // Reading from System.in

        Board board = new Board();
        board.initBoard();
        board.printBoard();
        log("");
        log("Hello, welcome to the most annoying Tic-Tac-Toe game ever!");
        log("");

        boolean hoomanTurn;
        String winner;


        //choose who goes first
        if(r.nextFloat() < 0.5)
        {
            //human first
            hoomanTurn = true;
            log("hooman");
        }
        else
        {
            //computer first
            hoomanTurn = false;
            log("computer");
        }

        //make a board
        //decide who goes first
        //pass board into loop

        while(!gameOver)
        {


            if(hoomanTurn)
            {
                //move and adjust the board
                log("You are O and it is your turn");
                String userEntry  = scanner.next();



            }
            else
            {
                log("The computer chose where to go");
                Move bestMove = board.findBestMove(board.make2Dee());
                //board at position bestMove.column, bestMove.row changed by findBestMove
                

            }

            //if(board.checkForVictory)
            //gameOver = true
            //winner = board.getVictor


        }

        //log("The winner is : " + winner");
        scanner.close();


    }
}
