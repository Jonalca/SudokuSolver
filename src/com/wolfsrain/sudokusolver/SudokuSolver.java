package com.wolfsrain.sudokusolver;

public class SudokuSolver {

    public static int[][] GRID_TO_SOLVE = {
            {3,0,0,1,0,0,0,0,9},
            {0,5,4,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,7},
            {6,0,0,3,0,0,0,0,2},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,6,7,0},
    };

    private int[][] board;

    private int EMPTY = 0;
    private int SIZE = 9;


    //Constructor gets a matrix (board) to solve
    public SudokuSolver(int[][] board) {
        this.board = board;
        this.SIZE = board.length;
    }

    //Does the number exist in the row?
    private boolean isInRow(int row, int number){
        for (int i = 0; i < SIZE; i++){
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }
    //Does the number exist in the column?
    private boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++){
            if (board[i][col] == number){
                return true;
            }
        }
        return false;
    }

    //Does the number exist in a 3x3 area?
    private boolean isInBox(int row, int col, int number){
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++){
            for (int j = c; j < c + 3; j++){
                if (board[i][j] == number){
                    return true;
                }

            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int number){
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }


    public boolean solveIt() {
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                //is the cell empty?
                if (board[row][col] == EMPTY) {
                    //Lets try all the numbers >:D
                    for (int number = 1; number <= SIZE; number++){
                        if (isValid(row, col, number)){
                            board[row][col] = number;
                            if (solveIt()) {
                                return true;
                            } else {
                                //System.out.println("Empty cell");
                                board[row][col] = EMPTY;
                            }
                        }
                    }
                    //System.out.println("What");
                    return false;
                }
            }
        }
        System.out.println("Solved!");
        return true; //sudoku solved
    }

    public void display(){

        for(int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args){

        SudokuSolver sudokuSolver = new SudokuSolver(GRID_TO_SOLVE);
        System.out.println("Sudoku to solve");
        sudokuSolver.display();
        System.out.println("Solving Sudoku...");

        if (sudokuSolver.solveIt()) {
            System.out.println("Result");
            sudokuSolver.display();
        } else {
            //TODO: Which the fuck did that?
            System.out.println("One constraint has been violated!");
        }
    }
}
