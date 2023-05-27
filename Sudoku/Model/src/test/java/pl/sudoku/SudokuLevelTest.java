package pl.sudoku;

import org.junit.jupiter.api.Test;
import pl.sudoku.SudokuBoard;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.SudokuLevel;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuLevelTest {

    @Test
    public void SudokuLevelEraseTest() {
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);
        int difficulty = 30;

        board.solveGame();

        var eraser = new SudokuLevel(difficulty);
        eraser.eraseSudokuFields(board);

        int numberOfZeros = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board.get(x, y) == 0) {
                    numberOfZeros++;
                }
            }
        }
        assertEquals(difficulty, numberOfZeros);
    }
}
