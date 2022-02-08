package com.der.main;

import com.der.graphics.Frame;
import com.der.main.closed.knightTour0.KnightTour0;
import com.der.main.open.knightTour1.KnightTour1;
import com.der.main.open.knightTour2.KnightTour2;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Main {

    public static int size, x0, y0;
    public static int[][] PathMatrix;

    public static void main(String[] args) {
        boolean again = true;
        boolean x0Check = false;
        boolean y0Check = false;
        boolean sizecheck = false;
        while (!sizecheck) {
            String input = JOptionPane.showInputDialog(null, "Enter Size", "Input", JOptionPane.PLAIN_MESSAGE);
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        " Size Is Requiered To Continue !", "No Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            size = Integer.parseInt(input);
            if (size < 5 || size > 9) {
                JOptionPane.showMessageDialog(null,
                        " Size Should Be Between 5 To 9", "Wrong Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            sizecheck = true;
        }
        while (!y0Check) {
            String input = JOptionPane.showInputDialog(null, "Enter Starting Point's X ", "Input", JOptionPane.PLAIN_MESSAGE);
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        " x Is Requiered To Continue !", "No Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            y0 = Integer.parseInt(input);
            if (y0 < 1 || y0 > size) {
                JOptionPane.showMessageDialog(null,
                        " X Should Be Between 1 To " + size, "Wrong Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            y0Check = true;
        }
        while (!x0Check) {
            String input = JOptionPane.showInputDialog(null, "Enter Starting Point's Y ", "Input", JOptionPane.PLAIN_MESSAGE);
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        " y Is Requiered To Continue !", "No Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            x0 = Integer.parseInt(input);
            if (x0 < 1 || x0 > size) {
                JOptionPane.showMessageDialog(null,
                        " Y Should Be Between 1 To " + size, "Wrong Input",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            x0Check = true;
        }
        int t = x0;
        x0 = y0;
        y0 = t;
        Tour();
        if (PathMatrix != null) {
            Frame frame = new Frame(PathMatrix, size * size, x0, y0);
            frame.setVisible(true);
        }
    }

    public static void Tour() {
        if (size == 8) {
            CallKnightTour0();
        } else if (size < 9) {
            CallKnightTour1();
        } else {
            CallKnightTour2();
        }
    }

    public static void CallKnightTour0() {//Warnsdorff' solution
        //size,x0,y0
        int x = x0 - 1;
        int y = y0 - 1;
        KnightTour0 kn = new KnightTour0(size, x, y);
        while (!kn.Solve()) { // tries until it finds an answer
            kn = new KnightTour0(size, x, y);
        }
        PathMatrix = kn.getResult();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(PathMatrix[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public static void CallKnightTour1() { //revursive
        KnightTour1 t = new KnightTour1(size);
        if (t.Solve(x0 - 1, y0 - 1)) {
            PathMatrix = t.TourPath();
        } else {
            CallKnightTour2();
        }
    }

    public static void CallKnightTour2() { //Warnsdorff’s Method + recursive
        int[] pos = {x0, y0};
        KnightTour2 solver = new KnightTour2();
        solver.StartSolving(size, pos);
        LinkedList<String> resultArray = solver.res();
        if (resultArray.size() + 1 < size * size) {
            JOptionPane.showMessageDialog(null, "Tour isn't possible!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int[][] matrix = new int[size + 1][size + 1];
            for (int i = 0; i <= size; i++) {
                for (int j = 0; j <= size; j++) {
                    matrix[i][j] = (size * size) - 1;
                }
            }
            for (int i = 0; i < resultArray.size(); i++) {
                String s = resultArray.get(i);
                int x = (Integer.parseInt(s.charAt(0) + ""));
                int y = (Integer.parseInt(s.charAt(1) + ""));
                matrix[x][y] = i;
            }
            int[][] result = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    result[i][j] = matrix[i + 1][j + 1];
                    System.out.print(result[i][j] + "  ");
                }
                System.out.println("");
            }
            PathMatrix = result;
        }
    }
}
