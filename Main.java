import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/inventory_team2.csv";
        CRUD crud = new CRUD(filePath);
        crud.read(crud.filePath);
        crud.create(crud.filePath);
        crud.update(crud.filePath);
        crud.delete(crud.filePath);
    }
}
