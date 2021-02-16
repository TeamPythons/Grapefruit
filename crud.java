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
    }

    public void save(String filePath) throws IOException{
        //Write the contents of each ArrayList into a new csv file
        FileWriter csvWriter = new FileWriter(filePath,true);
        String wholeDocument = "";
        for( int i = 0; i < productID.size(); i++){
            wholeDocument += productID.get(i)+","+quantity.get(i)+","+wholesaleCost.get(i)+","+salePrice.get(i)+","+supplierID.get(i)+"/n";
        }
        csvWriter.write(wholeDocument);
        csvWriter.flush();
        csvWriter.close();
    }

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
            if(dataToCompare == searchWord){
                searchResults.add(i);
            }
        }

        return searchResults;
    }

}
