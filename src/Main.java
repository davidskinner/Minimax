import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;



public class Main {



    public static void log(String v)
    {
        System.out.println(v);
    }


    public static void main(String[] args) {

        //decide who goes first
        //hooman: choose a space
        //place move
        //else comp do minimax
        //place move


        Random r = new Random();
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);  // Reading from System.in

        Board board = new Board();
        board.initBoard();
        board.printBoard();
        log("");
        log("Hello, welcome to Tic-Tac-Toe!");
        log("");

        boolean hoomanTurn = false;
        String winner = "winnerString";


        //choose who goes first
        if(r.nextFloat() < 0.5)
        {
            //human first
            hoomanTurn = true;
            log("hooman turn");
        }
        else
        {
            //computer first
            hoomanTurn = false;
            log("computer turn");
        }

        //make a board
        //decide who goes first
        //pass board into loop


        while(!gameOver)
        {
            if(hoomanTurn)
            {
                //move and adjust the board
                log("Your turn, hooman.");

                String userEntry  = scanner.next();

                Move hoomanMove = new Move();
                hoomanMove.row = Integer.valueOf(String.valueOf(userEntry.charAt(0)));
                hoomanMove.column = Integer.valueOf(String.valueOf(userEntry.charAt(1)));
                board.applyMove(hoomanMove,hoomanTurn);

                hoomanTurn = false;
                board.printBoard();
            }
            else
            {
                log("Computer turn");
                Move bestMove = board.findBestMove(board.board);
                board.applyMove(bestMove,hoomanTurn);

                hoomanTurn =true;
                board.printBoard();
            }

            //computer wins
            if(board.evaluate(board.board) == 10)
            {
                winner = "computer";
                break;
            }
            //hooman wins
            else if(board.evaluate(board.board) == -10)
            {
                winner = "hooman";
                break;
            }
            else
            {
                log("no one has won");
            }
        }

        log("The winner is : " + winner);

        scanner.close();
    }
}
