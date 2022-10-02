import java.util.*;
import java.io.*;

public class Process {

    private String filename;

    public Process(String filename) {
        this.filename = filename;
    }

    public void runAction() throws Exception {

        // We ensure we are receiving right .csv format before we start converting 
        int indexOf = filename.lastIndexOf(".");

        if (indexOf == -1)
            throw new Exception("Please Insert file including the .csv extension");

        String extension = filename.substring(indexOf);

        if (!extension.equalsIgnoreCase(".csv"))
            throw new Exception("Invalid extension type. Insert only .csv file");

        // We Start to read .csv file and throw exception if file not found
        Scanner input = null;

        try {
            input = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {

            throw new FileNotFoundException("The system cannot find the file specified");
      
        } catch (Exception e) {

            e.printStackTrace();
        }

        String [] headers = getHeaders(input);
        HashMap<String, List<Enrollee>> insuranceCompanies = new HashMap<>();

        while (input.hasNext()) {
            boolean dontInsert = false;

            // We read and create an object representation of each row in the .csv file
            String [] currentRow = input.nextLine().split(",");
            Enrollee enrollee = new Enrollee(currentRow[0], currentRow[1], Integer.parseInt(currentRow[2]), currentRow[3]);

            // We use a hashmap to separate all enrollees by insurance Company
            List<Enrollee> enrolleeList = insuranceCompanies.get(currentRow[3]);

            if (enrolleeList == null) 
                enrolleeList = new ArrayList<>();
                
            // Before we insert into our HashMap, we make sure that we are not inserting a enrollee with an existing 
            // userId
            for (Enrollee temp : enrolleeList) {
                if (enrollee.getUserId().equals(temp.getUserId())) {
                    
                    if (temp.getVersion() < enrollee.getVersion())
                        temp = enrollee;

                    dontInsert = true;
                    break;
                }
            }        
            if (dontInsert == false)
                enrolleeList.add(enrollee);

            insuranceCompanies.put(currentRow[3], enrolleeList);
        }

        // We traverse all insurance companies and process their enrolles
        for (Map.Entry<String, List<Enrollee>> entry : insuranceCompanies.entrySet()) {
            String insuranceCompany = entry.getKey();
            List<Enrollee> enrollees = entry.getValue();

            // We sort the list of enrolles in asceding order and based on their name
            enrollees.sort((e1, e2) -> e1.getFullName().compareTo(e2.getFullName()));

            // We create a new .csv file for each insurance company
            PrintWriter writer = new PrintWriter(insuranceCompany + ".csv", "UTF-8");
            writer.println(headers[0] + "," + headers[1] + "," + headers[2] + "," + headers[3]);

            for (Enrollee enrollee : enrollees)
                writer.println(enrollee.getUserId() + "," + enrollee.getFullName() + "," + enrollee.getVersion() + "," + enrollee.getInsuranceCompany());
            
            writer.close(); 
        }

        input.close();
    }

    // We collect all of the headers/columns from .csv file
    public static String [] getHeaders(Scanner input) throws Exception {

        if (!input.hasNext())
            throw new Exception("The .csv file is empty");

        String headers = input.nextLine();
        return headers.split(",", -1);
    }

    public static void main(String [] args) throws Exception {
        
        // We make sure we are receiving a file as a parameter
        if (args.length == 0) {
            System.out.println("Error: Insert vaild file in the format: java Converter <filename.csv>");
            System.exit(0);
        }

        Process process = new Process(args[0]);
        process.runAction();
    }
}