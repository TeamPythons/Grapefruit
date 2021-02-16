import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        //Initalize the list for return values
        List<String> listReturnValues = new ArrayList<String>(5);
        List<Integer> listLineNumbers = new ArrayList<Integer>(5);
        List<String> listFullValue = new ArrayList<String>(5);

        //Initalize the lineNumber value
        int lineNumber = 1;

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
        while ((line = csvReader.readLine()) != null) {
            values = line.split(",");
            if (values[column].equals(search)) {
                listReturnValues.add(values[column]);
                listLineNumbers.add(lineNumber);
                listFullValue.add(line);
            }
            lineNumber++;
        }
        csvReader.close();
        sc.close();

        // Return returnValue
        System.out.println(listReturnValues.toString());
        System.out.println(listLineNumbers.toString());
        System.out.println(listFullValue);
        return line;
    }

    private Boolean udHelper(String filePath, int lineToChange, Boolean update, String newline) throws IOException{
        //Helper function that can either Update (when update = true) OR Delete (when update = false)
        System.out.println("Helper function invoked");
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        int thisLine = 1;
        Boolean returnValue = false;
        String row;
        String wholeDocument = "";

        //Go through the file line by line and add the lines to wholeDocument, except for the deleted line
        while ((row = csvReader.readLine()) != null) {
            if(thisLine != lineToChange){
                wholeDocument += row + "\n";
            }else{
                returnValue = true;
                //if update is set to true, add the new line to the file
                if(update){
                    System.out.println("Updating line" + String.valueOf(thisLine));
                    wholeDocument += newline + "\n";
                }
                //if update is set to false, do nothing (just delete the existing line)
            }
        }
        csvReader.close();

        //Replace the CSV with the contents of wholeDocument;
        FileWriter csvWriter = new FileWriter(filePath,false);
        csvWriter.write(wholeDocument);
        csvWriter.flush();
        csvWriter.close();

        return returnValue;
    }

    //Update and Delete both call udHelper to do the hard work
    public Boolean update(String filePath, int lineToDelete, String Product_ID, String Quantity, String Wholesale_Cost, String Sale_Price, String Supplier_ID){
        try{
            return udHelper(filePath,lineToDelete,true,Product_ID+","+Quantity+","+Wholesale_Cost+","+Sale_Price+","+Supplier_ID);
        } catch(IOException ie) {
            return false;
        }
    }

    public Boolean delete(String filePath, int lineToDelete) throws IOException{
        try{
            return udHelper(filePath,lineToDelete,false,"");
        } catch(IOException ie) {
            return false;
        }
    }

}
