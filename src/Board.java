public class Board {

    char[] board = new char[9];

    public void initBoard()
    {
        String temp = "123456789";
        char[] tempCharArray = temp.toCharArray();
        System.arraycopy(tempCharArray,0,board,0,9);
    }
    public void printBoard()
    {
        System.out.println("/---|---|---\\");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("/---|---|---\\");
    }
    }


