package pl.sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Class for sudoku board with numbers.
 *
 * @author Barbara Borkowska and Aleksander Lemiesz
 */
public class SudokuBoard implements Serializable {
    private static final Logger logger = Logger.getLogger(SudokuBoard.class);
    /**
     * Variable for size of sudoku row.
     *
     * @rowSize defines size of the sudoku row
     */
    private final int rowSize = 9;
    /**
     * Variable for size of row in little square.
     *
     * @squareRowSize defines size of row in little square
     */
    private final int squareRowSize = 3;
    /**
     * Variable for size of row in little square.
     *
     * @boardSize defines size of the entire board
     */
    private final int boardSize = 81;
    /**
     * Array (changed to list) for the sudoku grid.
     *
     * @board is the sudoku grid
     */
    private List<SudokuField> board = Arrays.asList(new SudokuField[boardSize]);
    /**
     * Object for solving.
     *
     * @sudokuSolver is the interface for solving
     */
    private SudokuSolver sudokuSolver;

    /**
     * Constructor creating sudoku board.
     *
     * @param sudokuSolver chosen algorithm for solving sudoku
     */
    public SudokuBoard(SudokuSolver sudokuSolver) {

        this.sudokuSolver = sudokuSolver;

        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                this.set(column, row, 0);
            }
        }
    }

    /**
     * Returns the value of particular cell.
     *
     * @param column is x position of the cell
     * @param row    is y position of the cell
     * @return value of the cell on position (x, y)
     */
    public final int get(final int column, final int row) {
        return board.get(position(column, row)).getValue();
    }

    /**
     * Sets new value to particular cell.
     *
     * @param column   is x position of the cell
     * @param row      is y position of the cell
     * @param newValue new value of the cell on position (x, y)
     */
    public final void set(final int column,
                          final int row, final int newValue) {
        SudokuField suField = new SudokuField();
        suField.setValue(newValue);
        board.set(position(column, row), suField);
    }

    /**
     * Calculates position.
     *
     * @param column integer number of column in sudoku starting from 0
     * @param row    integer number of row in sudoku starting from 0
     * @return position of the cell
     */
    private final int position(final int column, final int row) {
        return column + row * rowSize;
    }

    /**
     * Gets copy of the sudoku array.
     *
     * @return copy of board
     */
    public final List<SudokuField> getBoard() {
        List<SudokuField> boardCopy = new ArrayList<>(board);
        return boardCopy;
    }

    /**
     * Translates sudoku to string.
     *
     * @return translated sudoku
     */
    @Override
    public final String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int row = 0; row < rowSize; row++) {
            if (row % squareRowSize == 0) {
                toReturn.append(horizontalBarToString());
            }
            for (int column = 0; column < rowSize; column++) {
                if (column == 0) {
                    toReturn.append("|");
                }
                toReturn.append(board.get(position(column, row)).getValue()).append(" ");
                if (column % squareRowSize == 2) {
                    toReturn.append("|");
                }
                toReturn.append(" ");
            }
            toReturn.append("\n");
            if (row == (rowSize - 1)) {
                toReturn.append(horizontalBarToString());
            }
        }
        return toReturn.toString();
    }

    /**
     * Makes horizontal bar as string.
     *
     * @return string as characters
     */
    private String horizontalBarToString() {
        StringBuilder toReturn = new StringBuilder();
        final int sizeOfBar = 30;
        for (int i = 0; i < sizeOfBar; i++) {
            toReturn.append("-");
        }
        toReturn.append("\n");
        return toReturn.toString();
    }

    /**
     * Solves the sudoku board.
     */
    public final void solveGame() {
        sudokuSolver.solve(this);
    }

    /**
     * Getter for row.
     *
     * @param rowNumber number of the chosen row
     * @return row at the given number
     */
    public SudokuRow getRow(int rowNumber) {
        ArrayList<SudokuField> rowContent = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            rowContent.add(i, board.get(position(i, rowNumber)));
        }
        SudokuRow row = new SudokuRow(rowContent);
        return row;
    }

    /**
     * Getter for column.
     *
     * @param columnNumber number of the chosen column
     * @return column at the given number
     */
    public SudokuColumn getColumn(int columnNumber) {
        ArrayList<SudokuField> columnContent = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            columnContent.add(i, board.get(position(columnNumber, i)));
        }
        SudokuColumn col = new SudokuColumn(columnContent);
        return col;
    }

    /**
     * Getter for box.
     *
     * @param column number of the column of the box
     * @param row    number of the row of the box
     * @return box at the given row and column
     */
    public SudokuBox getBox(int column, int row) {
        ArrayList<SudokuField> boxContent = new ArrayList<>();

        int squareRowBeginning = row - (row % squareRowSize);
        int squareColumnBeginning = column - (column % squareRowSize);

        for (int r = squareRowBeginning; r < squareRowBeginning + squareRowSize; r++) {
            for (int c = squareColumnBeginning; c < squareColumnBeginning + squareRowSize; c++) {
                boxContent.add(board.get(position(c, r)));
            }
        }
        SudokuBox box = new SudokuBox(boxContent);
        return box;
    }

    /**
     * Checks the correctness of the entire board.
     *
     * @return info whether the whole board is correct (true or false)
     */
    public boolean checkBoard() {
        for (int i = 0; i < rowSize; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }
        for (int col = 0; col < rowSize; col++) {
            for (int row = 0; row < rowSize; row++) {
                if (!getBox(col, row).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the sudoku is already solved.
     *
     * @return if the sudoku is solved
     */
    public final boolean isSolved() {
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < rowSize; column++) {
                if (get(column, row) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard board1 = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(board, board1.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard clone;
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            objectOutputStream.writeObject(this);

            try (var byteArrayInputStream =
                         new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                 var objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

                clone = (SudokuBoard) objectInputStream.readObject();

            }

            return clone;

        } catch (Exception e) {
            //String log4jConfPath = "E:\\Files\\Studia\\4thSemester\\ComponentProgramming"
            //        + "\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            String log4jConfPath = "D:\\PL_Files\\Component"
                    + "\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            logger.error(e.toString());
            return null;
        }
    }
}
