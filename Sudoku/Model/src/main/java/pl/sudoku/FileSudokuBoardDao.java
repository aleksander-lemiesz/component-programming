package pl.sudoku;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import pl.exceptions.DaoFileNotFoundException;
import pl.exceptions.FileDaoReadException;
import pl.exceptions.FileDaoWriteException;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private static final Logger logger = Logger.getLogger(FileSudokuBoardDao.class);
    private String filename;

    /**
     * Constructor setting up filename.
     * @param filename is a name of file to be opened and create appropriate stream
     */
    public FileSudokuBoardDao(String filename) {
            this.filename = filename;
    }

    @Override
    public void finalize() {
    }

    @Override
    public void close() {
    }

    @Override
    public SudokuBoard read() throws FileDaoReadException {
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);
        try (var stream = new FileInputStream(filename);
             var objectStream = new ObjectInputStream(stream)) {

            board = (SudokuBoard) objectStream.readObject();


        } catch (FileNotFoundException e) {
            Locale locale = Locale.getDefault();
            var bundle = ResourceBundle.getBundle("i18n.Language", locale);
            var error = new DaoFileNotFoundException(bundle.getString("unableToLoad"));
            logger.error(error.toString());
            throw error;

        } catch (IOException | ClassNotFoundException e) {
            Locale locale = Locale.getDefault();
            var bundle = ResourceBundle.getBundle("i18n.Language", locale);
            var error = new FileDaoReadException(bundle.getString("unableToLoad"));
            logger.error(error.toString());
            throw error;
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws FileDaoWriteException {
        try (var writeStream = new FileOutputStream(filename);
             var objectStream = new ObjectOutputStream(writeStream)) {

            objectStream.writeObject(obj);

        } catch (IOException e) {
            String log4jConfPath = "E:\\Files\\Studia\\4thSemester"
                    + "\\ComponentProgramming\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            //String log4jConfPath = "D:\\PL_Files\\Component"
            //        + "\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            Locale locale = Locale.getDefault();
            var bundle = ResourceBundle.getBundle("i18n.Language", locale);
            var error = new FileDaoWriteException(bundle.getString("unableToSave"));
            logger.error(error.toString());
            throw error;
        }
    }
}
