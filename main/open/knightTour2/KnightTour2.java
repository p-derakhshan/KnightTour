/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.der.main.open.knightTour2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Parya Derakhshan
 */
public class KnightTour2 {

    private static StringBuilder result;
    private static LinkedList<String> resultArray;
    private static int Size;

    public KnightTour2() {
        result = new StringBuilder();
        resultArray = new LinkedList<String>();
    }

    public static String getResult() {
        return result.toString();
    }

    public static LinkedList<String> res() {
        return resultArray;
    }

    public static boolean StartSolving(int size, int[] pos) { //gets size of the board
        Size = size;
        ChessBoard board = new ChessBoard(size); // creats the board
        Position start = new Position(pos[0], pos[1]); //gets the starting cell
        board.visit(start, true); //visits the first cell
        board.setKnightLocation(start); //puts the knight at starting cell
        return SolveTheRest(board); //continues on the rest of the board
    }

    private static boolean SolveTheRest(ChessBoard board) { //recursive
        if (board.IsAllVisited()) {
            return true;
        }
        Position[] possibleMoves = board.possibleMoves();
        //creats an array of all cells it can move to
        if (possibleMoves.length == 0) {
            return false;
        }
        int iterator = 0; //next
        for (int current = 0; current < possibleMoves.length; current++) { //the number of possible moves : max = 8
            int currentMoves = board.nextMoves(possibleMoves[current]).length;
            //number of possible moves of 'current' state
            int iteratorMoves = board.nextMoves(possibleMoves[iterator]).length;
            //number of possible moves of 'iterator' state
            if (currentMoves < iteratorMoves
                    || (currentMoves == iteratorMoves
                    && board.getDegree(possibleMoves[current]) < board.getDegree(possibleMoves[iterator]))) {
                iterator = current;
            }
        }
        // for all the possible next moves it choses the cell that can make less moves (closer to the walls)
        resultArray.add(board.getCoord(board.getKnightLocation()));
        result.append(board.getCoord(board.getKnightLocation())).append("\n");
        board.setKnightLocation(possibleMoves[iterator]); //moves to the next cell
        board.visit(possibleMoves[iterator], true); //visits the new cell
        return SolveTheRest(board); //continues on the rest of the board
    }
}
