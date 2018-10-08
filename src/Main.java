import java.util.Random;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        board.initBoard();
        board.printBoard();

        log("");
        log("Hello, welcome to the most annoying game of Tic-Tac-Toe!");
        log("");

        boolean hoomanTurn = false;
        String winner = "winnerString";

        //choose who goes first
        if(r.nextFloat() < 0.5)
        {
            //human first
            hoomanTurn = true;
        }
        else
        {
            //computer first
            hoomanTurn = false;
        }

        //main loop
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
            }
            else
            {
                log("Computer turn");
                Move bestMove = board.getmmMove(board.board);
                board.applyMove(bestMove,hoomanTurn);
                hoomanTurn =true;
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

            board.printBoard();
        }

        log("The winner is : " + winner);
        board.printBoard();

        scanner.close();
    }
}
