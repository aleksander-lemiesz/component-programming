package pl.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import pl.sudoku.SudokuBoard;

public class SudokuLevel {
    private int difficulty;
    private List<Pair> pairsList;

    /**
     * Constructor with adjustment of difficulty.
     * @param difficulty determines how many zeros are added
     */
    public SudokuLevel(int difficulty) {
        this.difficulty = difficulty;
        int numberOfValidPositions = 9;
        Pair[] pairs = new Pair[numberOfValidPositions * numberOfValidPositions];
        for (int x = 0; x < numberOfValidPositions; x++) {
            for (int y = 0; y < numberOfValidPositions; y++) {
                var pair = new Pair();
                pair.firstNumber = x;
                pair.secondNumber = y;
                pairs[x + y * 9] = pair;
            }
        }
        this.pairsList = Arrays.asList(pairs);
    }

    /**
     * Adds zeroes from board.
     * @param board is the object to have fields zeroed
     */
    public void eraseSudokuFields(SudokuBoard board) {
        Collections.shuffle(pairsList);
        for (int i = 0; i < difficulty; i++) {
            board.set(pairsList.get(i).firstNumber, pairsList.get(i).secondNumber, 0);
        }
    }

    class Pair {
        public int firstNumber;
        public int secondNumber;
    }

}
