
import java.io.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        //testing the drivers will go in here
        SQLDatabaseConnection sqlDriver = new SQLDatabaseConnection();

        sqlDriver.mainSqlDriver(readStringCreator());

        // "SELECT * FROM inventory_team2"
        

        

        
    }
    public static String readStringCreator() {
        // read string for sql query will be built here
        
        String sqlQuery = "SELECT * from inventory_team2" ;
        return sqlQuery;
    
    }
}