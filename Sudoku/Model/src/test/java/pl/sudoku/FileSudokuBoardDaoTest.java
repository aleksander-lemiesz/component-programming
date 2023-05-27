package pl.sudoku;

import org.junit.jupiter.api.Test;
import pl.exceptions.DaoFileNotFoundException;
import pl.exceptions.FileDaoReadException;
import pl.exceptions.FileDaoWriteException;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest() throws FileDaoWriteException, FileDaoReadException, DaoFileNotFoundException {
        String filename = "test.txt";
        var file = new FileSudokuBoardDao(filename);
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);
        board.solveGame();

        file.write(board);

        var boardCopy = file.read();
        assertEquals(board, boardCopy);
    }

    @Test
    public void constructorTest() throws IOException {
        String filename = "test.txt";
        var file = new FileSudokuBoardDao(filename);
        var newFile = new File(filename);
        assertFalse(newFile.createNewFile());
    }

    @Test
    public void closeTest() {
            String filename = "test.txt";
            var file = new FileSudokuBoardDao(filename);
    }
}
