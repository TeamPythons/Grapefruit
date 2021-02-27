import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {
    

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public void mainSqlDriver(String selectSql) {
        String connectionUrl =
                "jdbc:sqlserver://grapefruit-mango-s1.database.windows.net:1433;"
                + "database=grapefruit;"
                + "user=GrapeAdmin;"
                + "password=TreeRockCar2021;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + "" + resultSet.getString(5));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}