import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class crud {
    List<String> productID = new ArrayList<String>();
    List<String> quantity = new ArrayList<String>();
    List<String> wholesaleCost = new ArrayList<String>();
    List<String> salePrice = new ArrayList<String>();
    List<String> supplierID = new ArrayList<String>();

    public void printIndex(int index){
        System.out.println(productID.get(index)+", "+quantity.get(index)+", "+wholesaleCost.get(index)+", "+salePrice.get(index)+", "+supplierID.get(index));
    }

    public int getSize(){
        return productID.size();
    }

    public void replace(int category, int index, String newData){
        switch(category){
            case 0:
                productID.set(index, newData);
            break;
            case 1:
                quantity.set(index, newData);
            break;
            case 2:
                wholesaleCost.set(index, newData);
            break;
            case 3:
                salePrice.set(index, newData);
            break;
            case 4:
                supplierID.set(index, newData);
            break;
            default:
            break;
        }
    }

    public String getValue(int category, int row){
        String result = "";
            switch(category){
                case 0:
                    result = productID.get(row);
                break;
                case 1:
                    result = quantity.get(row);
                break;
                case 2:
                    result = wholesaleCost.get(row);
                break;
                case 3:
                    result = salePrice.get(row);
                break;
                case 4:
                    result = supplierID.get(row);
                break;
                default:
                    result = "";
                break;
            }
        return result;
    }

    public void load(String filePath, Boolean overwrite) throws IOException{
        //Load a file from a CSV document into the ArrayLists
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String line;
        String[] values;
        if(overwrite){
            productID.clear();
            quantity.clear();
            wholesaleCost.clear();
            salePrice.clear();
            supplierID.clear();
        }
        while ((line = csvReader.readLine()) != null) {
            values = line.split(",");
            productID.add(values[0]);
            quantity.add(values[1]);
            wholesaleCost.add(values[2]);
            salePrice.add(values[3]);
            supplierID.add(values[4]);
        }
        csvReader.close();
<<<<<<< HEAD
    }
=======
        sc.close();
>>>>>>> main

    public void save(String filePath) throws IOException{
        //Write the contents of each ArrayList into a new csv file
        FileWriter csvWriter = new FileWriter(filePath,false);
        String wholeDocument = "";
        for( int i = 0; i < productID.size(); i++){
            wholeDocument += productID.get(i)+","+quantity.get(i)+","+wholesaleCost.get(i)+","+salePrice.get(i)+","+supplierID.get(i)+"\n";
        }
        csvWriter.write(wholeDocument);
        csvWriter.flush();
        csvWriter.close();
    }

<<<<<<< HEAD
    public void delete(int index){
        productID.remove(index);
        quantity.remove(index);
        wholesaleCost.remove(index);
        salePrice.remove(index);
        supplierID.remove(index);
    }

    public List<Integer> search(int column, String searchWord){
        List<Integer> searchResults = new ArrayList<Integer>();
        String dataToCompare = "";
        //Search returns the indexes of the entries when found
        for(int i = 0; i < productID.size(); i++){
            switch(column){
                case 0:
                    dataToCompare = productID.get(i);
                break;
                case 1:
                    dataToCompare = quantity.get(i);
                break;
                case 2:
                    dataToCompare = wholesaleCost.get(i);
                break;
                case 3:
                    dataToCompare = salePrice.get(i);
                break;
                default:
                    dataToCompare = supplierID.get(i);
                break;
            }
            if(dataToCompare.equals(searchWord)){
                searchResults.add(i);
            }
        }

        return searchResults;
=======
    private Boolean udHelper(String filePath, int lineToChange, Boolean update, String newline) throws IOException{
        //Helper function that can either Update (when update = true) OR Delete (when update = false)
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
                    wholeDocument += newline + "\n";
                }
                //if update is set to false, do nothing (just delete the existing line)
            }
           thisLine ++;
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
>>>>>>> main
    }

}
