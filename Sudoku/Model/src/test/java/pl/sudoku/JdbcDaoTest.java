package pl.sudoku;

import org.junit.jupiter.api.Test;
import pl.exceptions.DaoException;
import pl.exceptions.DaoFileNotFoundException;
import pl.exceptions.FileDaoReadException;
import pl.exceptions.FileDaoWriteException;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JdbcDaoTest {

    @Test
    public void writeAndReadTest() throws DaoException {
        String name = "test.txt9";
        try (var jdbc = new JdbcSudokuBoardDao(name);) {

            var solver = new BacktrackingSudokuSolver();
            var board = new SudokuBoard(solver);
            board.solveGame();

            jdbc.write(board);

            var boardCopy = jdbc.read();
            assertEquals(board, boardCopy);
        }
    }


    @Test
    public void closeTest() throws DaoException {
        String name = "test.txt";
        var jdbc = new JdbcSudokuBoardDao(name);
        jdbc.close();
    }
}
