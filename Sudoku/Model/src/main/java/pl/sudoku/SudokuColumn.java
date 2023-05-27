package pl.sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class for verifying correctness of particular column content in sudoku.
 */
public class SudokuColumn extends Verifier implements Serializable, Cloneable {
    /**
     * Verifying the correctness of column.
     * @param colContent the content of the column in sudoku
     */
    public SudokuColumn(ArrayList<SudokuField> colContent) {
        super(colContent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

    @Override
    public SudokuColumn clone() {
        return (SudokuColumn) super.clone();
    }
}
