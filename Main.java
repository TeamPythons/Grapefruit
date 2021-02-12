import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "inventory_team2.csv";
        CRUD crud = new CRUD(filePath);
        //Add a test product to the file
        crud.create(crud.filePath,"TESTPRODUCT","69","1.21","420","TESTSELLER");
        crud.read(crud.filePath);
        crud.update(crud.filePath);
        crud.delete(crud.filePath);
    }
}
