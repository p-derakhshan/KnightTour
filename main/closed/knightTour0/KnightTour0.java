package com.der.main.closed.knightTour0;

import java.util.concurrent.ThreadLocalRandom; // to use random numbers in parallel in thread pools

public class KnightTour0 {

    private static int ChessSize;
    private static int path[];
    private static int x0, y0;

    public KnightTour0(int size, int x_start, int y_start) {
        ChessSize = size;
        x0 = x_start;
        y0 = y_start;
        path = new int[ChessSize * ChessSize];
        for (int i = 0; i < ChessSize * ChessSize; ++i) {
            path[i] = -1;
        }
    }

    private static final int move_x[] = {1, 1, 2, 2, -1, -1, -2, -2};
    private static final int move_y[] = {2, -2, 1, -1, 2, -2, 1, -1};

    private boolean IsSafe(int x, int y) { // next move is in the board and hasn't been visited yet
        boolean inBoard = ((x >= 0 && y >= 0) && (x < ChessSize && y < ChessSize));
        return (inBoard) && (path[y * ChessSize + x] < 0);
    }
    // if current cell is one move away from the starting point, the tour is finished

    private boolean ClosedTour(int x, int y) {
        for (int i = 0; i < 8; i++) {
            if (((x + move_x[i]) == x0) && ((y + move_y[i]) == y0)) {
                return true;
            }
        }
        return false;
    }

    private int Degree(int x, int y) { // number of possible moves from (x,y)
        int count = 0;
        for (int i = 0; i < ChessSize; ++i) {
            if (IsSafe((x + move_x[i]), (y + move_y[i]))) {
                count++;
            }
        }
        // Degree of each cell changes with each move
        return count;
    }

    private Cell nextLoc(Cell cell) { //the next move is the cell with minimum degree (Warnsdorff's heuristic)
        int chosen = -1;
        int minDeg = (ChessSize + 1);
        int nextX, nextY, nextDeg;

        int random = ThreadLocalRandom.current().nextInt(1000) % ChessSize;

        // checks all reachable cells and their degrees
        for (int count = 0; count < ChessSize; count++) {
            int next = (random + count) % ChessSize; //random next move ( starts from a random cell )
            nextX = cell.x + move_x[next];
            nextY = cell.y + move_y[next];
            nextDeg = Degree(nextX, nextY);
            if ((IsSafe(nextX, nextY)) && nextDeg < minDeg) {
                chosen = next;
                minDeg = nextDeg;
            }
        }

        if (chosen == -1) {
            return null; // could't pick a next cell
        }

        // picks the chosen next cell
        nextX = cell.x + move_x[chosen];
        nextY = cell.y + move_y[chosen];

        // goes to the chosen cell (visits)
        path[nextY * ChessSize + nextX] = path[(cell.y) * ChessSize + (cell.x)] + 1;
        //increases the number of moves

        cell.x = nextX;
        cell.y = nextY;

        return cell; //returns the chosen cell
    }

    public boolean Solve() {
        Cell cell = new Cell(x0, y0); //starting point
        path[cell.y * ChessSize + cell.x] = 1; //visits the first cell
        Cell next = null;
        for (int i = 0; i < ChessSize * ChessSize - 1; ++i) { //traces though the board
            next = nextLoc(cell); //chooses the next cell
            if (next == null) {
                return false;
            }
        }// tour is finished
        if (!ClosedTour(next.x, next.y)) { // checks if the tour is closed
            return false;
        }
        //  PrintPath();
        return true;
    }

    private void PrintPath() { //prints the path
        for (int i = 0; i < ChessSize; ++i) {
            for (int j = 0; j < ChessSize; ++j) {
                System.out.printf("%d\t", (path[j * ChessSize + i] - 1));
            }
            System.out.printf("\n");
        }
    }

    public int[][] getResult() {
        int[][] result = new int[ChessSize][ChessSize];
        for (int i = 0; i < ChessSize; ++i) {
            for (int j = 0; j < ChessSize; ++j) {
                result[i][j] = (path[j * ChessSize + i] - 1);
            }
        }
        return result;
    }
}
