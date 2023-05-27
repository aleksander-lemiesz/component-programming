package pl.sudoku;

import org.apache.log4j.Logger;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.PropertyConfigurator;
import pl.exceptions.*;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private static final Logger logger = Logger.getLogger(JdbcSudokuBoardDao.class);
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String jdbc_URL = "jdbc:derby:SudokuDB;";
    private Connection connection;
    private String name;

    /**
     * Creates JdbcSudokuBoardDao and establishes connection to the database.
     * @param name is name of particular sudoku
     * @throws DaoException thrown if unable to create Dao
     */
    public JdbcSudokuBoardDao(String name) throws DaoException {
        try {
            this.name = name;
            //Class.forName(driver);
            connection = DriverManager.getConnection(jdbc_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("UnableToCreateJdbcDao");
        }
    }

    @Override
    public SudokuBoard read() throws JdbcDaoReadException {
        try {
            ResultSet result = connection.createStatement().executeQuery(
                    "SELECT SUDOKU "
                    + "FROM BOARD "
                    + "WHERE NAME = '" + this.name + "'");

            ResultSetMetaData resultSetMetaData = result.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            String boardValues = "123";
            while (result.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    boardValues = result.getString(i);
                }
            }

            var values = new int[boardValues.length()];
            for (int i = 0; i < boardValues.length(); i++) {
                values[i] = Character.getNumericValue(boardValues.charAt(i));
            }
            var solver = new BacktrackingSudokuSolver();
            var board = new SudokuBoard(solver);
            final int rowSize = 9;


            for (int column = 0; column < rowSize; column++) {
                for (int row = 0; row < rowSize; row++) {
                    board.set(column, row, values[row + column * rowSize]);
                }
            }

            return board;

        } catch (SQLException e) {
            var error = new JdbcDaoReadException("unableToLoad");
            logger.error(error.toString());
            throw error;
        }
    }

    @Override
    public void write(SudokuBoard obj) throws JdbcDaoWriteException {
        try {
            var builder = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    builder.append(obj.get(i, j));
                }
            }

            connection.createStatement().execute(
                    "INSERT INTO BOARD VALUES ('" + this.name + "', '" + builder.toString() + "')");
                    //"insert into BOARD values " +
                    //        "('test.txt', '123456789123456789123456789123456789123456789123456789123456789123456789123456789')");

        } catch (SQLException e) {
            //String log4jConfPath = "E:\\Files\\Studia\\4thSemester"
            //        + "\\ComponentProgramming\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            String log4jConfPath = "D:\\PL_Files\\Component"
                    + "\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            var error = new JdbcDaoWriteException("unableToSave");
            logger.error(error.toString());
            throw error;
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("UnableToClose");
        }
    }

    @Override
    public void finalize() throws DaoException {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("UnableToClose");
        }
    }
}
