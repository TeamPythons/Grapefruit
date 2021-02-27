import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //testing the drivers will go in here
        
        SQLDatabaseConnection sqlDriver = new SQLDatabaseConnection();

        sqlDriver.mainSqlDriver(readStringCreator());

        // "SELECT * FROM inventory_team2"
        

        

        
    }
    public static String readStringCreator() {
        // read string for sql query will be built here
        Scanner scan = new Scanner(System.in);


        // basic user gui to guide the sql query string builder
        System.out.println("What information are you looking for?");
        System.out.println("(1):look up by product number");
        System.out.println("(2): look up by product quantity");
        System.out.println("(3): look up by wholesale cost");
        System.out.println("(4): look up by sale cost");
        System.out.println("(5): look up by supplier ID");

        String colTemp = scan.nextLine();
        int column = Integer.parseInt(colTemp);
        
        

        String colString; 
  
        // switch statement with int data type 
        switch (column) { 
        case 1: 
            colString = "product_id"; 
            break; 
        case 2: 
            colString = "quantity"; 
            break; 
        case 3: 
            colString = "wholesale_cost"; 
            break; 
        case 4: 
            colString = "sale_cost"; 
            break; 
        case 5: 
            colString = "supplier_id"; 
            break; 
        default: 
            colString = "Invalid input"; 
        } 
        
     
 

        System.out.println("please eneter the value of the information your looking for");
        String row = scan.nextLine();

        
        //building of the sql string
        String readString = ("SELECT * from inventory_team2 WHERE" + colString + " = " + row);
         
        String sqlQuery = readString ;
        return sqlQuery;
    
    }
}