import java.io.*;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
        public static void main(String[] args) throws IOException {
        String filePath = "inventory_team2.csv";
        crud db = new crud();
        Scanner scan = new Scanner(System.in);
        Boolean loop = true;

        //Logo
        System.out.println("Python Studio Presents...");
        System.out.println("  #####\n#     # #####    ##   #####  ###### ###### #####  #    # # ##### \n#       #    #  #  #  #    # #      #      #    # #    # #   #   \n#  #### #    # #    # #    # #####  #####  #    # #    # #   #   \n#     # #####  ###### #####  #      #      #####  #    # #   #   \n#     # #   #  #    # #      #      #      #   #  #    # #   #   \n #####  #    # #    # #      ###### #      #    #  ####  #   #  ");
        

        //password
        System.out.println("Enter the Secret Password (1234): ");
        String s = scan.nextLine();
        System.out.println(s);
        if(!s.equals("1234")){
            System.out.println("Bad password, terminating...");
            System.exit(1);
        }

        while(loop){
            //Menu
            System.out.println("\n\nEnter the number of the function you want:\n0: Load CSV File\n1: Search for a product\n2: Save changes\n3: Exit");
            int i = scan.nextInt();
            scan.nextLine(); //this is here to prevent skipping prompts later on
            switch(i){
                case 0: //Load File
                    System.out.println("Enter the file name to load, or press enter for <inventory_team2.csv>");
                    String fName = scan.nextLine();
                    if(fName.equals("")){fName = "inventory_team2.csv";}
                    System.out.println("Loading file: "+fName);
                    System.out.println("Replace existing contents? (Y/N)");
                    String ans = scan.nextLine();
                    Boolean overwrite = false;
                    if(ans.equals("Y") || ans.equals("y")){overwrite = true;}
                    System.out.println("Loading File...");

                    try{
                        db.load(fName,overwrite);
                    }catch(Exception e){
                        System.out.println("File not found :/");
                        break;
                    }

                    System.out.println("Success... Database now has "+String.valueOf(db.getSize())+" Entries\n\n");
                break;
                case 1: //Search
                    if(db.getSize() == 0){
                        System.out.println("Please load data into the database");
                        break;
                    }
                    System.out.println("Enter the category to search by:\n0: Product ID\n1: Quantity\n2: Wholesale Cost\n3: Sale Price\n4: Supplier ID");
                    int searchCategory = scan.nextInt();
                    scan.nextLine();
                    if(searchCategory >= 0 && searchCategory <= 4){
                        System.out.println("Word or number to search for: ");
                        String searchWord = scan.nextLine();
                        List<Integer> searchResults = db.search(searchCategory, searchWord);
                        System.out.println("Search Results: ");

                        if(searchResults.size() == 0){
                            System.out.println("None");
                            break;
                        }

                        //Loop through list of results and display them
                        for(i = 0; i < searchResults.size(); i++){
                            db.printIndex(searchResults.get(i));
                        }

                        System.out.println("---------\nWhat do you want to with this data?\n0: Change value\n1: Delete entries\n2: Nothing");
                        int promptAns = scan.nextInt();
                        scan.nextLine();
                        if(promptAns == 0){

                            //Replace items
                            System.out.println("Enter the category to replace:\n0: Product ID\n1: Quantity\n2: Wholesale Cost\n3: Sale Price\n4: Supplier ID");
                            int replaceCategory = scan.nextInt();
                            scan.nextLine();
                            if(replaceCategory >= 0 && replaceCategory <= 4){
                                System.out.println("Enter the new value:");
                                String newData = scan.nextLine();
                                for(i = 0; i < searchResults.size(); i++){
                                    db.replace(replaceCategory, searchResults.get(i), newData);
                                }
                                System.out.println("Done (made "+String.valueOf(i)+" Replacements)");
                            }else{
                                System.out.println("This is not a valid input... Back to main menu");
                            }

                        }else if(promptAns == 1){

                            //Delete items
                            for(i = 0; i < searchResults.size(); i++){
                                db.delete(searchResults.get(i));
                            }
                            System.out.println("Done... Deleted "+String.valueOf(i)+" items");
                        }

                    }else{
                        System.out.println("This is not a valid input... Try again");
                    }
                break;
                case 2:
                    //Save changes
                    if(db.getSize() == 0){
                        System.out.println("You have not modified the database yet (nothing to save)");
                        break;
                    }
                    System.out.println("Enter the file name to save to, or press enter for <inventory_team2.csv>");
                    String saveFile = scan.nextLine();
                    if(saveFile.equals("")){saveFile = "inventory_team2.csv";}
                    try{
                        db.save(saveFile);
                        System.out.println("File saved\n");
                    }catch(Exception e){
                        System.out.println("File doesn't exist");
                    }
                break;
                default:
                    loop = false;
                    System.out.println("Exiting...");
                break;
            }
            
            
        }
    }
}
