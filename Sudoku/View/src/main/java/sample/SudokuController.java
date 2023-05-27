package sample;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pl.exceptions.*;
import pl.sudoku.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SudokuController implements Initializable {

    @FXML private Pane pane;
    @FXML private Canvas canvas;
    @FXML private Button in8;
    @FXML private Button in4;
    @FXML private Button in6;
    @FXML private Button in7;
    @FXML private Button in2;
    @FXML private Button in3;
    @FXML private Button in5;
    @FXML private Button in9;
    @FXML private Button in1;
    private int player_selected_row;
    private int player_selected_col;
    private SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard copyBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    public void initData(SudokuBoard initialBoard) {
        board = initialBoard.clone();
        copyBoard = board.clone();
    }

    public void edit(SudokuBoard boardToEdit, int column, int row, int value) {
        boardToEdit.set(column, row, value);
    }

    public Property read(SudokuBoard boardToEdit, int column, int row) {
        return new SimpleStringProperty(String.valueOf(boardToEdit.get(column, row)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player_selected_row = 0;
        player_selected_col = 0;
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }

    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 450, 450);
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;
                context.setFill(Color.LAVENDER);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }
        if (board.get(player_selected_col, player_selected_row) == 0) {
            context.setStroke(Color.DARKRED);
            context.setLineWidth(3);
            context.strokeRoundRect(player_selected_col * 50 + 2, player_selected_row * 50 + 2, 46, 46, 10, 10);
        }
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if(board.get(col, row)!=0) {
                    context.fillText(board.get(col, row) + "", position_x, position_y);
                } else if(copyBoard.get(col, row)!=0) {
                    context.setFill(Color.RED);
                    context.fillText(copyBoard.get(col, row) + "", position_x, position_y);
                }
            }
        }
    }

    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouse_x = (int) event.getX();
                int mouse_y = (int) event.getY();
                player_selected_row = (int) (mouse_y / 50);
                player_selected_col = (int) (mouse_x / 50);
                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }

    public void buttonOnAction(ActionEvent event) {
        Button tempButton = (Button) event.getSource();
        if (board.get(player_selected_col, player_selected_row) == 0) {
            switch (tempButton.getId()) {
                case "in1":
                    copyBoard.set(player_selected_col, player_selected_row, 1);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in2":
                    copyBoard.set(player_selected_col, player_selected_row, 2);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in3":
                    copyBoard.set(player_selected_col, player_selected_row, 3);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in4":
                    copyBoard.set(player_selected_col, player_selected_row, 4);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in5":
                    copyBoard.set(player_selected_col, player_selected_row, 5);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in6":
                    copyBoard.set(player_selected_col, player_selected_row, 6);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in7":
                    copyBoard.set(player_selected_col, player_selected_row, 7);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in8":
                    copyBoard.set(player_selected_col, player_selected_row, 8);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
                case "in9":
                    copyBoard.set(player_selected_col, player_selected_row, 9);
                    drawOnCanvas(canvas.getGraphicsContext2D());
                    break;
            }
        }
    }


    public void saveSudokuToFile(ActionEvent event) throws JdbcDaoWriteException {
        try (var boardFile = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao("board.txt");
             var copyBoardFile = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao("copyBoard.txt");) {
            boardFile.write(board);
            copyBoardFile.write(copyBoard);
        } catch (DaoException e) {
            throw new JdbcDaoWriteException(e.getMessage());
        }
    }

    public void loadSudokuFromFile(ActionEvent event) throws JdbcDaoReadException {
        try (var boardFile = SudokuBoardDaoFactory.getJdbcDao("board.txt");
             var copyBoardFile = SudokuBoardDaoFactory.getJdbcDao("copyBoard.txt");) {
            board = boardFile.read();
            copyBoard = copyBoardFile.read();
        } catch (DaoException e) {
            throw new JdbcDaoReadException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SudokuBoard createBoardWithZeroes(int numberOfZeroes) {
        var solver = new BacktrackingSudokuSolver();
        var board = new SudokuBoard(solver);
        board.solveGame();

        var lvl = new SudokuLevel(numberOfZeroes);
        lvl.eraseSudokuFields(board);
        return board.clone();
    }
}