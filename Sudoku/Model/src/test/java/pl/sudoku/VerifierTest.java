package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VerifierTest {

    @Test
    public void verifierCorrectTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i);
            content.add(su);
        }
        Verifier ver = new Verifier(content);
        assertTrue(ver.verify());
    }

    @Test
    public void verifierWrongTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            su.setValue(1);
            content.add(su);
        }
        Verifier ver = new Verifier(content);
        assertFalse(ver.verify());
    }

    @Test
    public void verifyWithZerosTest() {
        ArrayList<SudokuField> content = new ArrayList<SudokuField>(9);
        for (int i = 1; i < 10; i++) {
            SudokuField su = new SudokuField();
            if (i == 2) {
                su.setValue(2);
            } else {
                su.setValue(0);
            }
            content.add(su);
        }
        Verifier ver = new Verifier(content);
        assertTrue(ver.verify());
    }

    @Test
    public void VerifierEqualsAndHashCodeTrueTest() {
        int listLength = 9;
        List<SudokuField> fieldList = Arrays.asList(new SudokuField[listLength]);
        for (int i = 0; i < listLength; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i + 1);
            fieldList.set(i, su);
        }

        var content = new ArrayList<SudokuField>(fieldList);
        var verOne = new Verifier(content);
        var verTwo = new Verifier(content);

        assertEquals(verOne, verTwo);
        assertEquals(verOne.hashCode(), verTwo.hashCode());

        assertEquals(verOne, verOne);
        assertEquals(verOne.hashCode(), verOne.hashCode());
    }

    @Test
    public void VerifierEqualsAndHashCodeFalseTest() {
        int listLength = 9;
        List<SudokuField> fieldList = Arrays.asList(new SudokuField[listLength]);
        for (int i = 0; i < listLength; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i + 1);
            fieldList.set(i, su);
        }

        var content = new ArrayList<SudokuField>(fieldList);
        var verOne = new Verifier(content);

        Collections.shuffle(content);
        var verTwo = new Verifier(content);

        assertNotEquals(verOne, verTwo);
        assertNotEquals(verOne.hashCode(), verTwo.hashCode());
        assertNotEquals(verOne, content);
        assertNotEquals(verOne.hashCode(), content.hashCode());
    }

    @Test
    public void verifierToStringTest() {
        int listLength = 2;
        List<SudokuField> fieldList = Arrays.asList(new SudokuField[listLength]);
        for (int i = 0; i < listLength; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i + 1);
            fieldList.set(i, su);
        }

        var content = new ArrayList<SudokuField>(fieldList);
        var verOne = new Verifier(content);
        System.out.println(verOne.toString());
    }

    @Test
    public void verifierCloneTest() {
        int listLength = 2;
        List<SudokuField> fieldList = Arrays.asList(new SudokuField[listLength]);
        for (int i = 0; i < listLength; i++) {
            SudokuField su = new SudokuField();
            su.setValue(i + 1);
            fieldList.set(i, su);
        }

        var content = new ArrayList<SudokuField>(fieldList);
        var ver = new Verifier(content);

        Verifier clone = ver.clone();
        assertEquals(ver, clone);

    }

}

