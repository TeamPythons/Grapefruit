import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CRUD {
    String[] values;
    String line = "";
    public String filePath = "";


    public CRUD(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        try{
            while((line = csvReader.readLine()) != null){
                values = line.split(",");
                System.out.println(values[1]);
            }
            csvReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public Boolean create(String filePath, String Product_ID, String Quantity, String Wholesale_Cost, String Sale_Price, String Supplier_ID) throws IOException{
        //Creates the fileWriter and adds a line to the end of the document
        //V2
        FileWriter csvWriter = new FileWriter(filePath,true);
        csvWriter.append(Product_ID + "," + Quantity + "," + Wholesale_Cost +"," + Sale_Price + "," + Supplier_ID + "\n");
        csvWriter.flush();
        csvWriter.close();
        return true;
    }
    public String read(String filePath) throws IOException {
        //Initalize the returnValue
        String returnValue = "";

        //Initalize a Scanner
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        //Ask which column they want to read from
        System.out.println("From what column do you want to pull info from?");
        System.out.println("Product_ID(0), Quantity(1), Wholesale_Cost(2), Sale_Price(3), Supplier_ID(4)");

        //Start Scanner
        Scanner sc = new Scanner(System.in);
        //Ask for column #
        int column = sc.nextInt();
        //Reading the leftover new line
        sc.nextLine();

        //Ask for search info
        System.out.println("Enter info for search");
        String search = sc.nextLine();

        //Loops and searches for Search Info in the column chosen
        while((line = csvReader.readLine()) != null){
            values = line.split(",");
            if (values[column].equals(search)){
                returnValue = line;
                break;
            }
        }
        csvReader.close();

        // Return returnValue
        System.out.println(returnValue);
        return returnValue;
    }

    public String update(String filePath){
        String returnValue = "";
        return returnValue;
    }

    public String delete(String filePath){
        String returnValue = "";
        return returnValue;
    }

}
