package pl.sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class for verifying correctness of particular row content in sudoku.
 */
public class SudokuRow extends Verifier implements Serializable {
    /**
     * Verifying the correctness of row.
     * @param rowContent the content of the row in sudoku
     */
    public SudokuRow(ArrayList<SudokuField> rowContent) {
        super(rowContent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

    @Override
    public SudokuRow clone() {
        return (SudokuRow) super.clone();
    }
}
