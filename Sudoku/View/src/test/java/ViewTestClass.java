import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;
import pl.exceptions.DaoFileNotFoundException;
import pl.exceptions.FileDaoReadException;
import pl.exceptions.FileDaoWriteException;
import pl.exceptions.JdbcDaoReadException;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.FileSudokuBoardDao;
import pl.sudoku.SudokuBoard;
import sample.SudokuController;

public class ViewTestClass {


    @Test
    public void autoClosableResourcesTest() throws DaoFileNotFoundException, FileDaoReadException,
            FileDaoWriteException, JdbcDaoReadException {

        // Creating files
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);
        try (var file = new FileSudokuBoardDao("board.txt");
             var copyFile = new FileSudokuBoardDao(("copyBoard.txt"));) {

            file.write(board);
            copyFile.write(board);
        }
        var controller = new SudokuController();
        controller.loadSudokuFromFile(new ActionEvent());
        controller.loadSudokuFromFile(new ActionEvent());
    }

}
