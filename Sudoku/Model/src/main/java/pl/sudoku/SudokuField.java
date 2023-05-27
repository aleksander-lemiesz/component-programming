package pl.sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for operating on one particular sudoku field and its content.
 */
public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {
    private int value;
    private static final Logger logger = LoggerFactory.getLogger(SudokuField.class);

    /**
     * getter for the value of sudoku field.
     *
     * @return value of the particular field
     */
    public int getValue() {
        return value;
    }

    /**
     * setter for new value in sudoku field.
     *
     * @param value the new value that is about to be set to the particular field
     */
    public void setValue(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        } else {
            logger.error("Value outside of 0-9 interval");
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuField)) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder()
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    public int compareTo(SudokuField sudokuField) {
        return this.value - sudokuField.value;
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}
