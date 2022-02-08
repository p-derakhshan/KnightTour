/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.der.main.open.knightTour2;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Parya Derakhshan
 */
public class ChessBoard {

    private static int[][] Moves = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    private int Size;
    private boolean[][] visited;
    private Position knightPosition = new Position(1, 1);
    private int[][] degree;

    public ChessBoard(int size) {
        this.Size = size;
        visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                visited[i][j] = false;
            }
        }
        setDegree();
    }

    private void setDegree() {
        //
        degree = new int[Size][Size];
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == Size - 1)
                        || (i == Size - 1 && j == Size - 1)
                        || (i == Size - 1 && j == 0)) {
                    degree[i][j] = 2; //at the corners
                } else if ((i == 1 && j == 0) || (i == 1 && j == Size - 1)
                        || (i == Size - 2 && j == Size - 1)
                        || (i == Size - 2 && j == 0) || (i == 0 && j == 1)
                        || (i == 0 && j == Size - 2)
                        || (i == Size - 1 && j == Size - 2)
                        || (i == Size - 1 && j == 1)) {
                    degree[i][j] = 3;// at the first/last (row/column)
                } else if ((i == 1 && j == 1) || (i == 1 && j == Size - 2)
                        || (i == Size - 2 && j == Size - 2)
                        || (i == Size - 2 && j == 1)
                        || (i == 0 && (j >= 2 && j < 6))
                        || ((i >= 2 && i < 6) && j == 0)
                        || (i == Size - 1 && (j >= 2 && j < 6))
                        || ((i >= 2 && i < 6) && j == Size - 1)) {
                    degree[i][j] = 4; // at the second-first/last (row/column)
                } else if ((i == 1 && (j >= 2 && j < 6))
                        || ((i >= 2 && i < 6) && j == 1)
                        || (i == Size - 2 && (j >= 2 && j < 6))
                        || ((i >= 2 && i < 6) && j == Size - 2)) {
                    degree[i][j] = 6; // at the second-first/last row and any column
                } else {
                    degree[i][j] = 8; // at any other cell
                }
            }
        }
    }

    public int getDegree(Position pos) { // number of possible moves 
        //it varies with each move
        return degree[pos.X() - 1][pos.Y() - 1];
    }

    public int getSize() {
        return Size;
    }

    public void visit(Position p, boolean b) {
        this.visited[p.X() - 1][p.Y() - 1] = b;
    }

    public boolean isVisited(Position p) {
        return this.visited[p.X() - 1][p.Y() - 1];
    }

    public void setKnightLocation(Position p) {
        this.knightPosition = p;
    }

    public Position getKnightLocation() {
        return knightPosition;
    }

    public boolean IsAllVisited() {
        for (boolean[] bool : visited) {
            for (boolean wasOn : bool) {
                if (!wasOn) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getCoord(Position p) {
        return "" + toChar(p.X()) + p.Y();
    }
    public int getIntPos(Position p){
        return (p.X()*10) + p.Y();
    }

    private String toChar(int x) { // to print with alphabet+numbers
        /* String result = "";
                while (true) {
            if (x > 26) {
                result += (char) ((x % 26) + 64);
                x /= 26;
            } else {
                result += (char) (x + 64);
                return result;
            }
        }*/
        return "" + x;
    }

    public Position[] possibleMoves() {
        LinkedList<Position> result = new LinkedList<Position>();
        for (int[] currentMove : Moves) {
            int x = knightPosition.X() + currentMove[0];
            int y = knightPosition.Y() + currentMove[1];
            if (x > 0 && y > 0 && x <= Size && y <= Size) { // check if its inside the board
                Position possiblePos = new Position(x, y);
                if (!isVisited(possiblePos)) {
                    result.add(possiblePos);
                }
            }
        }
        return result.toArray(new Position[result.size()]);
    }

    public Position[] nextMoves(Position position) {
        List<Position> result = new LinkedList<Position>();
        for (int[] posMove : Moves) {
            int x = position.X() + posMove[0];
            int y = position.Y() + posMove[1];
            if (x > 0 && y > 0 && x <= Size && y <= Size) {
                Position possiblePos = new Position(x, y);
                if (!isVisited(possiblePos)) {
                    result.add(possiblePos);
                }
            }
        }
        return result.toArray(new Position[result.size()]);
    }
}
