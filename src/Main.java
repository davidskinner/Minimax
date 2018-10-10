import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void log(String v) {
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
        log("To place your move, enter two numbers from the board as they are given. Such as, 23");
        log("");

        boolean hoomanTurn = true;
        String winner = "";

//        //choose who goes first
//        if (r.nextFloat() < 0.5) {
//            //human first
//            hoomanTurn = true;
//        } else {
//            //computer first
//            hoomanTurn = false;
//        }

        //main loop
        while (!gameOver) {
            if (hoomanTurn) {
                log("Your turn, hooman.");
                boolean  validMove = false;

                //take input until it is a valid input
                while(!validMove)
                {
                    String userEntry = String.valueOf(scanner.next());
                    Move hoomanMove = new Move();

                    if(userEntry.length() != 2)
                    {
                        //invalid move
                        log("make sure you enter 2 numbers");
                        validMove = false;
                    }
                    else
                    {
                        hoomanMove.row = Integer.valueOf(String.valueOf(userEntry.charAt(0)));
                        hoomanMove.column = Integer.valueOf(String.valueOf(userEntry.charAt(1)));
                        log(String.valueOf(userEntry.length()));
                        if(board.board[hoomanMove.row-1][hoomanMove.column-1].length() == 1)
                        {
                            log("invalid move");
                            validMove = false;
                        }
                        else
                        {
                            validMove = true;
                            board.applyMove(hoomanMove, hoomanTurn);
                            hoomanTurn = false;
                            break;
                        }
                    }
                }
            } else {
                log("Computer turn");
                Move bestMove = board.getMiniMaxMove(board.board);
                board.applyMove(bestMove, hoomanTurn);
                hoomanTurn = true;
            }

            //computer wins
            if (board.checkForWinner(board.board) == 10) {
                winner = "computer";
                break;
            }
            //hooman wins
            if (board.checkForWinner(board.board) == -10) {
                winner = "hooman";
                break;
            }

            //you tied
            if(!board.stillMove(board.board)){
                break;
            }

            board.printBoard();
        }
        if(!winner.equals(""))
        log("The winner is : " + winner);
        else
        log("You Tied!");

        board.printBoard();
        scanner.close();
    }
}
