package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    @Test
    public void correctNumberTest() {
        final int numberInInterval = 5;
        SudokuField su = new SudokuField();
        su.setValue(numberInInterval);
        assertEquals(su.getValue(), numberInInterval);
    }

    @Test
    public void tooHighNumberTest() {
        final int numberOutsideOfInterval = 11;
        final int numberInInterval = 5;
        SudokuField su = new SudokuField();
        su.setValue(numberInInterval);
        assertEquals(su.getValue(), numberInInterval);
        su.setValue(numberOutsideOfInterval);
        assertEquals(su.getValue(), numberInInterval);
    }

    @Test
    public void tooLowNumberTest() {
        final int numberOutsideOfInterval = -1;
        final int numberInInterval = 5;
        SudokuField su = new SudokuField();
        su.setValue(numberInInterval);
        assertEquals(su.getValue(), numberInInterval);
        su.setValue(numberOutsideOfInterval);
        assertEquals(su.getValue(), numberInInterval);
    }

    @Test
    public void SudokuFieldEqualsAndHashCodeTrueTest() {
        var fieldOne = new SudokuField();
        fieldOne.setValue(1);
        var fieldTwo = new SudokuField();
        fieldTwo.setValue(1);

        assertEquals(fieldOne, fieldTwo);
        assertEquals(fieldOne.hashCode(), fieldTwo.hashCode());
        assertEquals(fieldOne, fieldOne);
        assertEquals(fieldOne.hashCode(), fieldOne.hashCode());
    }

    @Test
    public void SudokuFieldEqualsAndHashCodeFalseTest() {
        var fieldOne = new SudokuField();
        fieldOne.setValue(1);
        var fieldTwo = new SudokuField();
        fieldTwo.setValue(2);

        var solver = new BacktrackingSudokuSolver();

        assertNotEquals(fieldOne, fieldTwo);
        assertNotEquals(fieldOne.hashCode(), fieldTwo.hashCode());

        assertNotEquals(fieldOne, solver);
        assertNotEquals(fieldOne.hashCode(), solver.hashCode());
    }

    @Test
    public void SudokuFieldCompareToTest() {
        int sameValue = 5;
        int smallerValue = 4;
        int biggerValue = 6;
        var s1 = new SudokuField();
        var s2 = new SudokuField();

        s1.setValue(sameValue);
        s2.setValue(sameValue);
        assertSame(0, s1.compareTo(s2));

        s1.setValue(smallerValue);
        s2.setValue(biggerValue);
        assertSame(-2, s1.compareTo(s2));

        s1.setValue(biggerValue);
        s2.setValue(smallerValue);
        assertSame(2, s1.compareTo(s2));
    }

    @Test
    public void SudokuFieldCloneTest() throws CloneNotSupportedException {
        var field = new SudokuField();
        SudokuField clone = field.clone();
        assertEquals(field, clone);

        int fieldValue = field.getValue();
        if (fieldValue == 9) {
            clone.setValue(fieldValue - 1);
        } else {
            clone.setValue(fieldValue + 1);
        }
        assertNotEquals(field, clone);
    }

}
