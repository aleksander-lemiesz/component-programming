package pl.sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An algorithn for sudoku solving: backtracking.
 */
public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    /**
     * Defines the size of sudoku row and column.
     *
     * @rowSize defines size of the sudoku row
     */
    private final int rowSize = 9;
    /**
     * Defines how many values there are.
     *
     * @numberOfValidValues defines the size of list of valid numbers
     */
    private final int numberOfValidValues = 9;
    /**
     * Has numbers from 1 to 9 to fill the sudoku.
     *
     * @validValuesList enables randomness by shuffle function
     */
    private List<Integer> validValuesList;

    /**
     * Constructor filling in validValuesList.
     */
    public BacktrackingSudokuSolver() {
        Integer[] validValues = new Integer[numberOfValidValues];
        for (int i = 1; i < validValues.length + 1; i++) {
            validValues[i - 1] = i;
        }
        validValuesList = Arrays.asList(validValues);
    }

    /**
     * Solves sudoku according to the rules.
     *
     * @param board sudoku board from class SudokuBoard
     * @return Whether sudoku was solved or not
     */
    public final boolean solve(final SudokuBoard board) {
        if (board.isSolved()) {
            return true;
        }

        int row = -1;
        int column = -1;

        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < rowSize; c++) {
                if (board.get(c, r) == 0) {
                    row = r;
                    column = c;
                    break;
                }
            }
        }

        Collections.shuffle(validValuesList);

        for (int i = 0; i < numberOfValidValues; i++) {
            board.set(column, row, validValuesList.get(i));
            if (!board.checkBoard()) {
                board.set(column, row, 0);
                continue;
            }
            if (solve(board)) {
                return true;
            } else {
                board.set(column, row, 0);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("rowSize", rowSize)
                .append("numberOfValidValues", numberOfValidValues)
                .append("validValuesList", validValuesList)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BacktrackingSudokuSolver)) {
            return false;
        }

        BacktrackingSudokuSolver that = (BacktrackingSudokuSolver) o;

        return new EqualsBuilder()
                .append(rowSize, that.rowSize)
                .append(numberOfValidValues, that.numberOfValidValues)
                .append(validValuesList, that.validValuesList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(rowSize)
                .append(numberOfValidValues)
                .append(validValuesList)
                .toHashCode();
    }
}
