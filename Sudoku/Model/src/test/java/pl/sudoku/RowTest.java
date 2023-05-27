package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RowTest {

    @Test
    public void rowTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuRow row = new SudokuRow(content);
        assertTrue(row.verify());
    }

    @Test
    public void rowToStringTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuRow row = new SudokuRow(content);
        System.out.println(row);
    }

    @Test
    public void rowCloneTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuRow row = new SudokuRow(content);
        SudokuRow clone = row.clone();
        assertEquals(row, clone);
    }
}
