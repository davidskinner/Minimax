import static java.lang.Math.min;

public class Board {

    String[][] board = new  String[3][3];
    private String computer = "X";
    private String hooman = "O";

//    public void initBoard()
//    {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                board[i][j] = '_';
//            }
//        }
//    }

    public void initBoard()
    {
        board[0][0] = "11";
        board[0][1] = "12";
        board[0][2] = "13";
        board[1][0] = "21";
        board[1][1] = "22";
        board[1][2] = "23";
        board[2][0] = "31";
        board[2][1] = "32";
        board[2][2] = "33";

    }

    public void printBoard()
    {
        System.out.println("/---|---|---\\");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("/---|---|---\\");
    }

    // This will return the best possible move for the player
    Move getmmMove(String board[][])
    {
        int bestVal = -200;
        Move mmMove = new Move();
        mmMove.row = -1;
        mmMove.column = -1;
        // Traverse all cells, evaluate minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i<3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                // Check if cell is empty
                if (board[i][j].length() > 1)
                {
                    String undo = "";
                    undo = board[i][j];
                    // Make the move
                    board[i][j] = computer;

                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = undo;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        mmMove.row = i;
                        mmMove.column = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return mmMove;
    }

    public void applyMove(Move move, boolean hoomanTurn)
    {
        if(hoomanTurn)
        {
            board[move.row-1][move.column-1] = hooman;
        }
        else
        {
            board[move.row][move.column] = computer;
        }
    }

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
    int minimax(String board[][], int depth, boolean isMax)
    {
        int score = evaluate(board);

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10)
            return score - depth;

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10)
            return score + depth;

        // If there are no more moves and no winner then
        // it is a tie
        if (!isMovesLeft(board))
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
                    if (board[i][j].length() > 1)
                    {
                        String undo = "";
                        undo = board[i][j];
                        // Make the move
                        board[i][j] = computer;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max( best,
                                minimax(board, depth+1, !isMax) );

                        // Undo the move
                        board[i][j] = undo;
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
                    if (board[i][j].length() > 1)
                    {
                        String undo = "";
                        undo = board[i][j];
                        // Make the move
                        board[i][j] = hooman;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = min(best,
                                minimax(board, depth+1, !isMax));

                        // Undo the move
                        board[i][j] = undo;
                    }
                }
            }
            return best;
        }
    }

    // This is the evaluation function
    int evaluate(String b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row<3; row++)
        {
            if (b[row][0]==b[row][1] &&
                    b[row][1]==b[row][2])
            {
                if (b[row][0] == computer)
                    return +10;
                else if (b[row][0]==hooman)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col<3; col++)
        {
            if (b[0][col]==b[1][col] &&
                    b[1][col]==b[2][col])
            {
                if (b[0][col]==computer)
                    return +10;

                else if (b[0][col]==hooman)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0]==b[1][1] && b[1][1]==b[2][2])
        {
            if (b[0][0]==computer)
                return +10;
            else if (b[0][0]==hooman)
                return -10;
        }

        if (b[0][2]==b[1][1] && b[1][1]==b[2][0])
        {
            if (b[0][2]==computer)
                return +10;
            else if (b[0][2]==hooman)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    public boolean isMovesLeft(String board[][])
    {
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (board[i][j].length() > 1)
                    return true;
        return false;
    }

    }


