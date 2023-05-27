package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import pl.sudoku.SudokuBoard;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    static Logger logger = Logger.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {


        var locale = new Locale("en", "UK");
        Locale.setDefault(locale);
        var bundle = ResourceBundle.getBundle("i18n.Language", locale);
        Parent root = FXMLLoader.load(getClass().getResource("difficultyScene.fxml"), bundle);
        primaryStage.setTitle("Sudoku Game :)");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        String log4jConfPath = "E:\\Files\\Studia\\4thSemester\\ComponentProgramming\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
        //String log4jConfPath = "D:\\PL_Files\\Component\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger.info("Opening Game");
        launch(args);
    }
}
