package pl.sudoku;

import java.io.Serializable;

/**
 * Interface for choosing algorithm for solving sodoku.
 */
public interface SudokuSolver {
    /**
     * Virtual function to solve sudoku.
     *
     * @param board sudoku board from class SudokuBoard
     * @return Whether sudoku was solved or not
     */
    boolean solve(SudokuBoard board);

}
