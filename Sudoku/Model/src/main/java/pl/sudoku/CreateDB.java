package pl.sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String jdbc_URL = "jdbc:derby:SudokuDB;create=True";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(jdbc_URL);
        connection.createStatement().execute
                ("create table BOARD(NAME varchar(20), SUDOKU varchar(81))");

        connection.createStatement().execute
                ("insert into BOARD values " +
                "('test', '123456789123456789123456789123456789123456789123456789123456789123456789123456789')");
    }

    public static void create() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(jdbc_URL);
        connection.createStatement().execute
                ("create table BOARD(NAME varchar(20), SUDOKU varchar(81))");
    }

    public static void delete() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(jdbc_URL);
        connection.createStatement().execute
                ("drop table BOARD");
    }
}
