//Game.java 
//Hi guys this is a simple TicTacToe games which we played in paper which is converted into java format Code


import java.util.Scanner; 
import java.util.Random;

public class TicTacToeAI {

    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        System.out.println("=== TIC TAC TOE (You vs Computer) ===");
        System.out.println("You are X, Computer is O");

        printBoard();

        while (true) {

            playerMove();
            if (checkWinner('X')) {
                printBoard();
                System.out.println("🎉 You Win!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a Draw!");
                break;
            }

            computerMove();
            if (checkWinner('O')) {
                printBoard();
                System.out.println("💻 Computer Wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a Draw!");
                break;
            }

            printBoard();
        }

        scanner.close();
    }

    // Print Board
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Player Move
    public static void playerMove() {
        int row, col;

        while (true) {
            System.out.print("Enter row (1-3): ");
            row = scanner.nextInt() - 1;

            System.out.print("Enter column (1-3): ");
            col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = 'X';
                break;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    // Computer Move (Simple AI - Random)
    public static void computerMove() {
        int row, col;

        System.out.println("Computer is making a move...");

        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (board[row][col] == ' ') {
                board[row][col] = 'O';
                break;
            }
        }
    }

    // Check Winner
    public static boolean checkWinner(char symbol) {

        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;

            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
                return true;
        }

        // Diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;

        return false;
    }

    // Check Draw
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;

        return true;
    }
}