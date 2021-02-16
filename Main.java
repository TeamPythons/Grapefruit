import java.io.*;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws IOException {
        String filePath = "inventory_team2.csv";
        crud db = new crud();

        //Logo
        System.out.println("Python Studio Presents...");
        System.out.println("  #####\n#     # #####    ##   #####  ###### ###### #####  #    # # ##### \n#       #    #  #  #  #    # #      #      #    # #    # #   #   \n#  #### #    # #    # #    # #####  #####  #    # #    # #   #   \n#     # #####  ###### #####  #      #      #####  #    # #   #   \n#     # #   #  #    # #      #      #      #   #  #    # #   #   \n #####  #    # #    # #      ###### #      #    #  ####  #   #  ");
        System.out.println("\n\nEnter the number of the function you want:\n0: Load CSV File\n1: Save CSV File\n2: Search for a product\n3: Add to database from CSV file\n4: Delete an entry by product ID");

    }
}
