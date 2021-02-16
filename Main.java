import java.io.*;

public class Main {
        public static void main(String[] args) throws IOException {
        String filePath = "inventory_team2.csv";
        CRUD crud = new CRUD(filePath);
        crud.read(crud.filePath);
        crud.create(crud.filePath,"testproduct","1234","5678","9ABC","6969");
        crud.update(crud.filePath,3,"AAA","AAA","AAA","AAA","AAA");
        crud.delete(crud.filePath,2);
    }
}
