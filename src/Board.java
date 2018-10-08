import static java.lang.Math.min;

public class Board {

    String[][] board = new  String[3][3];
    private String computer = "X";
    private String hooman = "O";

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
        System.out.println("/--------------\\");
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " ");
        System.out.println("---------------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " ");
        System.out.println("---------------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " ");
        System.out.println("/--------------\\");
    }

    Move getmmMove(String board[][])
    {
        int bestVal = -200;
        Move mmMove = new Move();
        mmMove.row = -1;
        mmMove.column = -1;

        for (int i = 0; i<3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                if (board[i][j].length() > 1)
                {
                    String undo = "";
                    undo = board[i][j];
                    board[i][j] = computer;

                    int moveVal = minimax(board, 0, false);

                    board[i][j] = undo;

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

    int minimax(String board[][], int depth, boolean isMax)
    {
        int score = evaluate(board);

        if (score == 10)
            return score - depth;

        if (score == -10)
            return score + depth;

        if (!isMovesLeft(board))
            return 0;

        if (isMax)
        {
            int best = -1000;

            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    if (board[i][j].length() > 1)
                    {
                        String undo = "";
                        undo = board[i][j];
                        board[i][j] = computer;

                        best = Math.max( best,
                                minimax(board, depth+1, !isMax) );

                        board[i][j] = undo;
                    }
                }
            }
            return best;
        }

        else
        {
            int best = 200;

            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    if (board[i][j].length() > 1)
                    {
                        String undo = "";
                        undo = board[i][j];
                        board[i][j] = hooman;

                        best = min(best,
                                minimax(board, depth+1, !isMax));

                        board[i][j] = undo;
                    }
                }
            }
            return best;
        }
    }

    int evaluate(String b[][])
    {
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


