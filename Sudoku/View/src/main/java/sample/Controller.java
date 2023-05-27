package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.SudokuBoard;
import pl.sudoku.SudokuLevel;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable{
    @FXML
    private Button languageButton;
    @FXML
    GridPane apMain;
    @FXML
    private Button normal;
    @FXML
    private Button hard;
    @FXML
    private Button easy;

    @FXML
    private Label buttonLabel;

    @FXML
    private Label author1;
    @FXML
    private Label author2;

    @FXML
    void buttonOnAction(ActionEvent event) {
        try {
            SudokuBoard board;

            Button tempButton = (Button) event.getSource();
                switch (tempButton.getId()) {
                case "easy":
                    board = createBoardWithZeroes(20);
                    break;
                case "normal":
                    board = createBoardWithZeroes(30);
                    break;
                case "hard":
                    board = createBoardWithZeroes(40);
                    break;
                default:
                    board = createBoardWithZeroes(0);
                    break;
            }
            ResourceBundle bundle;

            if (languageButton.getText().equals("English")) {
                var locale = new Locale("en", "UK");
                bundle = ResourceBundle.getBundle("i18n.Language", locale);

            } else /*if (languageButton.getText().equals("Polski"))*/ {
                bundle = ResourceBundle.getBundle("i18n.Language");

            }
            var loader = new FXMLLoader(getClass().getResource("sudokuScene.fxml"), bundle);
            var root = (Parent) loader.load();

            SudokuController controller = loader.getController();
            controller.initData(board);

            Stage appStage = (Stage) tempButton.getScene().getWindow();
            appStage.setScene(new Scene(root,720, 480));
            appStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.authors.Authors_en_UK");
        author1.setText(bundle.getObject("author1").toString());
        author2.setText(bundle.getObject("author2").toString());

    }

    private SudokuBoard createBoardWithZeroes(int numberOfZeroes) {
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);
        board.solveGame();

        var lvl = new SudokuLevel(numberOfZeroes);
        lvl.eraseSudokuFields(board);

        return board.clone();
    }

    public void langOnActionButton(ActionEvent event) throws IOException {
        Button tempButton = (Button) event.getSource();
        Locale locale;
        ResourceBundle bundle;

        FXMLLoader loader;
        ResourceBundle authorsBundle;

        if (languageButton.getText().equals("Polski")) {
            locale = new Locale("en", "UK");
            Locale.setDefault(locale);
            bundle = ResourceBundle.getBundle("i18n.Language", locale);
            loader = new FXMLLoader(getClass().getResource("difficultyScene.fxml"), bundle);
            authorsBundle = ResourceBundle.getBundle("i18n.authors.Authors_en_UK");

        } else /*if (languageButton.getText().equals("English"))*/ {
            locale = new Locale("pl", "PL");
            Locale.setDefault(locale);
            bundle = ResourceBundle.getBundle("i18n.Language");
            loader = new FXMLLoader(getClass().getResource("difficultyScene.fxml"), bundle);
            authorsBundle = ResourceBundle.getBundle("i18n.authors.Authors_pl_PL");

        }

        var root = (Parent) loader.load();

        Controller controller = loader.getController();

        controller.getAuthor1().setText(authorsBundle.getObject("author1").toString());
        controller.getAuthor2().setText(authorsBundle.getObject("author2").toString());

        Stage appStage = (Stage) tempButton.getScene().getWindow();
        appStage.setScene(new Scene(root,720, 480));
        appStage.show();

    }

    public Label getAuthor1() {
        return author1;
    }

    public Label getAuthor2() {
        return author2;
    }
}
