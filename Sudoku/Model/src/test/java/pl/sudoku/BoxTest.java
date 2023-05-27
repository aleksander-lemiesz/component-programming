package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoxTest {

    @Test
    public void boxTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuBox box = new SudokuBox(content);
        assertTrue(box.verify());
    }

    @Test
    public void boxToStringTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuBox box = new SudokuBox(content);
        System.out.println(box);
    }

    @Test
    public void boxCloneTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        SudokuBox box = new SudokuBox(content);
        SudokuBox clone = box.clone();
        assertEquals(box, clone);
    }
}
