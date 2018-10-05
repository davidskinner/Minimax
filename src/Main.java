import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;



public class Main {

    char player = 'x', opponent = 'o';



    public static void log(String v)
    {
        System.out.println(v);
    }

    // This function returns true if there are moves
// remaining on the board. It returns false if
// there are no moves left to play.
    boolean isMovesLeft(char board[][])
    {
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (board[i][j]=='_')
                    return true;
        return false;
    }

    // This is the evaluation function as discussed
// in the previous article ( http://goo.gl/sJgv68 )
    int evaluate(char b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row<3; row++)
        {
            if (b[row][0]==b[row][1] &&
                    b[row][1]==b[row][2])
            {
                if (b[row][0]==player)
                    return +10;
                else if (b[row][0]==opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col<3; col++)
        {
            if (b[0][col]==b[1][col] &&
                    b[1][col]==b[2][col])
            {
                if (b[0][col]==player)
                    return +10;

                else if (b[0][col]==opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0]==b[1][1] && b[1][1]==b[2][2])
        {
            if (b[0][0]==player)
                return +10;
            else if (b[0][0]==opponent)
                return -10;
        }

        if (b[0][2]==b[1][1] && b[1][1]==b[2][0])
        {
            if (b[0][2]==player)
                return +10;
            else if (b[0][2]==opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
    int minimax(char board[][], int depth, boolean isMax)
    {
        int score = evaluate(board);

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and no winner then
        // it is a tie
        if (isMovesLeft(board)==false)
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]=='_')
                    {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max( best,
                                minimax(board, depth+1, !isMax) );

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]=='_')
                    {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = min(best,
                                minimax(board, depth+1, !isMax));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }



    // This will return the best possible move for the player
    Move findBestMove(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.column = -1;

        // Traverse all cells, evaluate minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i<3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                // Check if celll is empty
                if (board[i][j]=='_')
                {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.column = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        log("The value of the best Move is : " + String.valueOf(bestVal));

        return bestMove;
    }

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

        //choose who goes first
        if(r.nextFloat() < 0.5)
        {
            //human first
            log("human");
        }
        else
        {
            //computer first
            log("computer");
        }

        while(!gameOver)
        {
            String userEntry  = scanner.next();
//            log(userEntry);

            if(userEntry.equals("x"))
            gameOver = true;

//                Rule 1: If I have a winning move, take it.
//                Rule 2: If the opponent has a winning move, block it.
//                Rule 3: If I can create a fork (two winning ways) after this move, do it.
//                Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
//                Rule 5: Place in the position such as I may win in the most number of possible ways.


        }





        scanner.close();




    }
}
