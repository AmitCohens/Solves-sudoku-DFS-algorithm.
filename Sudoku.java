import java.util.ArrayList;

public class Sudoku {
    private int[][] board;
    private ArrayList<int[][]> solves;

    public Sudoku(int length) {
        board = new int[length][length];
        solves = new ArrayList<>();
    }

    private int cubify(int i, int k) {
        k = (int) Math.sqrt(k);
        return (i / k) * k;
    }

    private boolean legal(int[][] board, int i, int j, int k) {
        int temp;
        for (temp = 0; temp < board.length; temp++) {
            if (board[i][temp] == k)
                return false;
        }
        for (temp = 0; temp < board.length; temp++) {
            if (board[temp][j] == k)
                return false;
        }
        i = cubify(i, board.length);
        j = cubify(j, board.length);
        int f = (int) Math.sqrt(board.length) - 1;
        for (int p = i; p <= i + f; p++) {
            for (int r = j; r <= j + f; r++) {
                if (board[p][r] == k)
                    return false;
            }
        }
        return true;
    }

    public ArrayList solve(int[][] board) {
        int[][] a = new int[board.length][board.length];
        return solve(board, a, 0, 0);
    }

    private ArrayList solve(int[][] board, int[][] solved, int i, int j) {
        if (i == board.length) {
            copyArray(board, solved);
            solves.add(solved);
            return solves;
        }
        if (j == board.length) {
            return solve(board, solved, i + 1, 0);
        }
        if (board[i][j] != 0) {
            return solve(board, solved, i, j + 1);
        }
        for (int k = 1; k <= board.length; k++) {
            if (legal(board, i, j, k)) {
                board[i][j] = k;
                solve(board, solved, i, j + 1);
                board[i][j] = 0;
            }
        }
        return solves;
    }

    public void copyArray (int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++)
            System.arraycopy(a[i], 0, b[i], 0, a.length);
    }
}