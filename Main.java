import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        SudokuGraphics s = new SudokuGraphics();
        f.add(s);
        f.setSize(500,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}