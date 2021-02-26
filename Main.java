import java.io.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) throws IOException {

        String connectionUrl = "jdbc:sqlserver://grapefruit-mango-s1.database.windows.net:1433;"
                + "database=Grapefruit;" + "user=GrapeAdmin;" + "password=TreeRockCar2021;" + "encrypt=true;"
                + "trustServerCertificate=false;" + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT quantity FROM inventory_team2 WHERE quantity = 1";

            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " testcorrect");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
