package com.der.main.open.knightTour1;

public class KnightTour1 {

    static int Size;
    private static final int[] move_x = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] move_y = {1, 2, 2, 1, -1, -2, -2, -1};
    private static int[][] path;

    public KnightTour1(int n) {
        this.Size = n;
        path = new int[Size][Size];
    }

    private static boolean IsSafe(int x, int y) {
        //checks if cell is inside the board and hasn't been visited
        if (x >= 0 && x < Size && y >= 0 && y < Size && path[x][y] == -1) {
            return true;
        }
        return false;
    }

    public static boolean Solve(int x0, int y0) {

        // default value = -1
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                path[x][y] = -1;
            }
        }

        // the knight is initially at the first block 
        path[x0][y0] = 0;

        // Start from (0,0) and explore all tours possible using Solve()
        if (!KnightTourSolver(x0, y0, 1)) {
            System.err.println("solution does not exist!");

            return false;
        } else {
            printSolution();
        }

        return true;
    }

    private static boolean KnightTourSolver(int x, int y, int currentCell) {
        //recursive

        if (currentCell == Size * Size) {
            //if (currentCell == answer[0][0]) {
            //all the cells have been visited
            return true;
        }

        int next_x, next_y;

        // Try all next moves from the current coordinate (x,y) --> reachable cells
        for (int k = 0; k < 8; k++) {
            next_x = x + move_x[k];
            next_y = y + move_y[k];
            if (IsSafe(next_x, next_y)) {
                path[next_x][next_y] = currentCell;
                if (KnightTourSolver(next_x, next_y, currentCell + 1)) {
                    return true;
                } else {
                    path[next_x][next_y] = -1;
                }
            }
        }

        return false;
    }

    public static void printSolution() {
        //print answer matrix
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                System.out.print(path[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] TourPath() {
        return path;
    }
}
