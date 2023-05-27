package pl.sudoku;

import pl.exceptions.DaoException;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public static Dao<SudokuBoard> getJdbcDao(String filename) throws DaoException {
        return new JdbcSudokuBoardDao(filename);
    }

    private SudokuBoardDaoFactory() {
    }
}
