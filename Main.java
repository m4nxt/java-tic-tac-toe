import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int player = -1; // 1: x, 2: o
        // initially set to 2 because line 14:18
        int gameOver = 0;
        int[][] board = { 
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0} 
        };  // 0 = empty, 1 = o, 1 = x
        formatBoard(board);
        while (gameOver == 0) {
            if (player == -1) { // alternate between players.
                player = 1;
            } else {
                player = -1;
            }
            board = playerTurn(board, player);
            formatBoard(board);
            gameOver = testForWin(board, player)[0];
        }
        System.out.println(player + " wins!");
    }

    public static int[][] playerTurn (int[][] board, int player) {
        class methods { // first 2 methods are called by the methods below!
            public static int[] rawPlayerInput () {
                // this method is only ever called by the getAndCheckPlayerInput () method!!
                Scanner scanner = new Scanner(System.in);
                System.out.print("Column: ");
                int col = scanner.nextInt();
                System.out.print("Row: ");
                int row = scanner.nextInt();
                int[] token = { row, col };
                return token;
            }

            public static int[] getAndCheckPlayerInput (int[][] board) {
                // this method is only ever called by the updateBoard () method!!
                int[] token = { -1, -1, 0 }; // column, row, bool (valid?)
                int[] raw = rawPlayerInput();
                while (token[2] == 0) {
                    if (board[raw[0]][raw[1]] == 0) {
                        for (int i = 0; i < 2; i++) {
                            token[i] = raw[i]; // write token if input is valid
                        }
                        token[2] = 1;
                    } else {
                        raw = rawPlayerInput(); // repeat method above if input is invalid
                    }
                }
                return token;
            }

            public static int[][] updateBoard (int[][] board, int player) {
                int[] infoStream = getAndCheckPlayerInput(board);
                int[][] newBoard = board;
                newBoard[infoStream[0]][infoStream[1]] = player;
                return newBoard;
            }
        }
        return methods.updateBoard(board, player);
    }

    public static void formatBoard (int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static int[] testForWin (int[][] board, int player) {
        int sum;
        int[] token = { 0, 0 }; // gameOver (bool), player
        // test for sum of rows
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += board[i][j];
            }
            if (sum == 3) {
                token[0] = 1;
                token[1] = player;
                return token;
            }
        }

        // test for sum of collumns
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += board[j][i];
            }
            if (sum == 3) {
                token[0] = 1;
                token[1] = player;
                return token;
            }
        }

        // test for first diagonal
        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += board[i][i];
        }
        if (sum == 3) {
            token[0] = 1;
            token[1] = player;
            return token;
        }

        // test for second diagonal
        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += board[i][2-i];
        }
        if (sum == 3) {
            token[0] = 1;
            token[1] = player;
            return token;
        }

        return token;
    }
}