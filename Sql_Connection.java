
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sql_Connection extends Connection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials

    public void update() {

        // after column is specifed choose what row to update

        Scanner locationInput = new Scanner(System.in);  // Create a Scanner object to determine what column
        System.out.println("what product do you want to update? Please enter product_id");

        String userLocation = locationInput.nextLine();  // read and store user input into variable

        // determine from user input what to specify update

        Scanner columnInput = new Scanner(System.in);  // Create a Scanner object to determine what column
        System.out.println("what about this product do you want to update? quantity wholesale_cost,sale_cost, supplier_id");

        String userColumn = columnInput.nextLine();  // read and store user input into variable

        Scanner updateInput = new Scanner(System.in); // new scanner to accept the input of change
        System.out.println("What do you want to change it to");

        String userInput = updateInput.nextLine(); // reads the users input of what they want to change it to



        // build a string which will be used as the argument of the sql statment query
        String updateSql = ("UPDATE inventory_team2 SET" + userColumn + "=" + userInput + "WHERE product_id = " + userLocation);


            
            
         resultSet = statement.executeQuery(selectSql);

         // Print results from select statement
         while (resultSet.next()) {
             System.out.println(resultSet.getString(1)+" testcorrect");
        
    }
}