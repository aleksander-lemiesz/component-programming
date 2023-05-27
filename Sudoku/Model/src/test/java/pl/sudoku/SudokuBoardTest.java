package pl.sudoku;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    private final int rowSize = 9;
    static Logger logger = Logger.getLogger(SudokuBoardTest.class);
    @Test
    public void printingSudoku() {

        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        //logger.info(sudoku.toString());
        System.out.println(sudoku.toString());
        //logger.info("Solving...");
        System.out.println("Solving...");
        sudoku.solveGame();
        //logger.info(sudoku.toString());
        System.out.println(sudoku.toString());
    }

    @Test
    public void printingBacktrackingSudokuSolver() {
        var solver = new BacktrackingSudokuSolver();
        System.out.println(solver.toString());
    }

    @Test
    public void checkDifference() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuFirst = new SudokuBoard(sudokuSolver);
        SudokuBoard sudokuSecond = new SudokuBoard(sudokuSolver);

        sudokuFirst.solveGame();
        sudokuSecond.solveGame();

        assertNotEquals(sudokuFirst.getBoard(), sudokuSecond.getBoard());
    }

    @Test
    public void validateSudoku() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        sudoku.solveGame();
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < rowSize; column++) {
                assertTrue(checkRow(column, row, sudoku));
                assertTrue(checkColumn(column, row, sudoku));
                assertTrue(checkSquare(column, row, sudoku));
            }
        }
        assertTrue(sudoku.checkBoard());
    }

    @Test
    public void getRowTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        for (int i = 0; i < rowSize; i++) {
            sudoku.set(i, 0, i + 1);
        }
        SudokuRow row = sudoku.getRow(0);
        for (int i = 0; i < rowSize; i++) {
            assertEquals(row.getContent().get(i).getValue(), i + 1);
        }
    }

    @Test
    public void getColumnTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        for (int i = 0; i < rowSize; i++) {
            sudoku.set(0, i, i + 1);
        }
        SudokuColumn col = sudoku.getColumn(0);
        for (int i = 0; i < rowSize; i++) {
            assertEquals(col.getContent().get(i).getValue(), i + 1);
        }
    }

    @Test
    public void getBoxTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        int j = 1;
        for (int row = 0; row < Math.sqrt(rowSize); row++) {
            for (int column = 0; column < Math.sqrt(rowSize); column++) {
                sudoku.set(column, row, j);
                j++;
            }
        }
        SudokuBox row = sudoku.getBox(0, 0);
        for (int i = 0; i < rowSize; i++) {
            assertEquals(row.getContent().get(i).getValue(), i+1);
        }
    }

    @Test
    public void checkBoardWrongTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        for (int col = 0; col < rowSize; col++) {
            for (int row = 0; row < rowSize; row++) {
                sudoku.set(col, row, 1);
            }
        }
        assertFalse(sudoku.checkBoard());
    }

    @Test
    public void isSolvedTestNotSolved() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
        assertFalse(sudoku.isSolved());
    }

    @Test
    public void SudokuBoardAndSudokuSolverEqualsAndHashCodeTrueTest() {
        var solverOne = new BacktrackingSudokuSolver();
        var boardOne = new SudokuBoard(solverOne);

        var solverTwo = new BacktrackingSudokuSolver();
        var boardTwo = new SudokuBoard(solverTwo);

        assertEquals(boardOne, boardOne);
        assertEquals(boardOne.hashCode(), boardOne.hashCode());
        assertEquals(solverOne, solverOne);
        assertEquals(solverOne.hashCode(), solverOne.hashCode());

        assertEquals(boardOne, boardTwo);
        assertEquals(boardOne.hashCode(), boardTwo.hashCode());
        assertEquals(solverOne, solverTwo);
        assertEquals(solverOne.hashCode(), solverTwo.hashCode());
    }

    @Test
    public void SudokuBoardAndSudokuSolverEqualsAndHashCodeFalseTest() {
        var solverOne = new BacktrackingSudokuSolver();
        var boardOne = new SudokuBoard(solverOne);

        var solverTwo = new BacktrackingSudokuSolver();
        var boardTwo = new SudokuBoard(solverTwo);
        boardTwo.solveGame();

        assertNotEquals(boardOne, boardTwo);
        assertNotEquals(boardOne.hashCode(), boardTwo.hashCode());
        assertNotEquals(boardOne, solverTwo);
        assertNotEquals(boardOne.hashCode(), solverTwo.hashCode());
        assertNotEquals(solverOne, boardTwo);
        assertNotEquals(solverOne.hashCode(), boardTwo.hashCode());
    }

    @Test
    public void sudokuBoardCloneTest() {
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);

        SudokuBoard clone = board.clone();
        assertEquals(board, clone);

        int fieldValue = board.get(0, 0);
        if (fieldValue == 9) {
            clone.set(0, 0, fieldValue - 1);
        } else {
            clone.set(0, 0, fieldValue + 1);
        }
        assertNotEquals(board, clone);
    }

    private boolean checkRow(int col, int row, SudokuBoard sudoku) {
        for (int c = 0; c < rowSize; c++) {
            if (c != col) {
                if (sudoku.get(c, row) == sudoku.get(col, row)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkColumn(int col, int row, SudokuBoard sudoku) {
        for (int r = 0; r < rowSize; r++) {
            if (r != row) {
                if (sudoku.get(col, r) == sudoku.get(col, row)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSquare(int col, int row, SudokuBoard sudoku) {
        int squareRowSize = 3;
        int squareRowBeginning = row - (row % squareRowSize);
        int squareColumnBeginning = col - (col % squareRowSize);

        for (int r = squareRowBeginning; r < squareRowBeginning + squareRowSize; r++) {
            for (int c = squareColumnBeginning; c < squareColumnBeginning + squareRowSize; c++) {

                if (col != c && row != r) {
                    if (sudoku.get(c, r) == sudoku.get(col, row)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
