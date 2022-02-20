import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;

public class SudokuGraphics extends JPanel implements ActionListener {
    private JLabel lblWelcome;
    private JButton cmdSolve, cmdNew;
    private JButton[][] cmdXY;
    private int[][] board;

    public SudokuGraphics() {
        String[] options = {"4X4", "9X9", "16X16"};
        int n = JOptionPane.showOptionDialog(this, "Choose board size:", "Board Size", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        n = (n + 2) * (n + 2);
        lblWelcome = new JLabel("Welcome!");
        lblWelcome.setFont(new Font("David",Font.BOLD,25));
        cmdSolve = new JButton("Solve");
        board = new int[n][n];
        cmdXY = new JButton[n][n];
        cmdNew = new JButton("New board");
        cmdSolve.addActionListener(this);

        JPanel top = new JPanel();
        top.add(lblWelcome);

        JPanel bottom = new JPanel();
        bottom.add(cmdSolve);

        JPanel gridBoard = new JPanel();
        int boxes = (int)(Math.sqrt(n));
        GridLayout gl = new GridLayout(n,n,3,3);
        gridBoard.setLayout(gl);

        for (int i = 0; i < cmdXY.length; i++) {
            for (int j = 0; j < cmdXY.length; j++) {
                cmdXY[i][j] = new JButton();
                cmdXY[i][j].setFont(new Font("David", Font.PLAIN, 20));
                cmdXY[i][j].setBackground(Color.WHITE);
                cmdXY[i][j].addActionListener(this);
                gridBoard.add(cmdXY[i][j]);
            }
        }

        this.setLayout(new BorderLayout());
        this.add(gridBoard, BorderLayout.CENTER);
        this.add(top, BorderLayout.PAGE_START);
        this.add(bottom, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdSolve) {
            Sudoku sudoku = new Sudoku(board.length);
            int[][] res = new int[board.length][board.length];
            ArrayList solves = new ArrayList();
            solves = sudoku.solve(this.board);
            sudoku.copyArray((int[][])(solves.get(0)), res);

            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board.length; j++) {
                    cmdXY[i][j].setText("" + res[i][j]);
                    cmdXY[i][j].setBackground(Color.GREEN);
                }
            }

            JOptionPane.showMessageDialog(this, "Solved! Found " + solves.size() + " solutions!");
        }
        else {
            int x = ((JButton) (e.getSource())).getX();
            int y = ((JButton) (e.getSource())).getY();
            int i = 0, j = 0, a, b;
            a = b = cmdXY.length;
            for (i = 0; i < cmdXY.length; i++) {
                for (j = 0; j < cmdXY.length; j++) {
                    if (cmdXY[i][j].getX() == x && cmdXY[i][j].getY() == y) {
                        a = i;
                        b = j;
                    }
                }
            }
            if (a == cmdXY.length || b == cmdXY.length) return;
            String s = "";
            int num = -1;
            do {
                s = JOptionPane.showInputDialog(this, "Enter number:");
                if (StringLegal(s, this.board.length)) {
                    num = Integer.parseInt(s);
                    break;
                }
            } while (true);
            board[a][b] = num;
            cmdXY[a][b].setText(s);
        }
    }
    private boolean StringLegal(String s, int n) {
        if (s.length() != 1) return false;
        String res = "" + n;
        return s.charAt(0) >= '0' && s.charAt(0) <= res.charAt(0);
    }
}