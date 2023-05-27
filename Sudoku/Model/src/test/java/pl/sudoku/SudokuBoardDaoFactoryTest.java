package pl.sudoku;

import org.junit.jupiter.api.Test;
import pl.exceptions.DaoException;
import pl.exceptions.DaoFileNotFoundException;
import pl.exceptions.FileDaoReadException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDaoTest() throws DaoException {
        String filename = "test.txt";
        var fileOne = SudokuBoardDaoFactory.getFileDao(filename);
        var fileTwo = new FileSudokuBoardDao(filename);

        assertEquals(fileOne.read(), fileTwo.read());
    }

}
