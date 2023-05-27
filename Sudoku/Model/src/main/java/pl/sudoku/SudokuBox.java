package pl.sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class for verifying correctness of particular box content in sudoku.
 */
public class SudokuBox extends Verifier implements Serializable, Cloneable {
    /**
     * Verifying the correctness of box.
     * @param boxContent the content of the box in sudoku
     */

    public SudokuBox(ArrayList<SudokuField> boxContent) {
        super(boxContent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

    @Override
    public SudokuBox clone() {
        return (SudokuBox) super.clone();
    }
}
