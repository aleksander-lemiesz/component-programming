package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColumnTest {

    @Test
    public void columnTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuColumn column = new SudokuColumn(content);
        assertTrue(column.verify());
    }

    @Test
    public void columnToStringTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuColumn column = new SudokuColumn(content);
        System.out.println(column);
    }

    @Test
    public void columnCloneTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuColumn column = new SudokuColumn(content);
        SudokuColumn clone = column.clone();
        assertEquals(column, clone);
    }
}
