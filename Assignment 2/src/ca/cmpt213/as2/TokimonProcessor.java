package ca.cmpt213.as2;
import java.util.*;
import java.io.*;

/**
 * This class does the following:
 * -> Checks if the arguments are valid.
 * -> Stores all .json files into an arraylist by calling findRecursively function of ReadJson class.
 * -> Passes the .json files to ReadJsonFile function of the ReadJson class.
 * -> Finally, it stores tokimon information in an Arraylist of string arrays.
 * -> Sorts it using sort function in CsvGenerator class.
 * -> Outputs the CSV file using outputCsv function of CsvGenerator class.
 */

public class TokimonProcessor {


    public static void main(String[] args) throws FileNotFoundException,IOException{

        if(args.length != 2){
            System.out.println("Exactly 2 arguments are accepted: \n 1) Your .json file directory. \n 2) Path to where you want your .csv file to be generated.");
            System.exit(0);
        }

        File input = new File(args[0]);
        File initial_output = new File(args[1]);

        if (!input.isDirectory()){
            System.out.println("The input file path you entered do not exist");
            System.exit(0);
        }
        if(!initial_output.isDirectory()){
            System.out.println("The output directory do not exist");
            System.exit(0);
        }
        File output = new File(initial_output+"/team_info.csv");

        ArrayList<File> allFiles = new ArrayList<File>();

        ReadJson test1 = new ReadJson();
        test1.findJsonRecursively(input,allFiles);
        if (allFiles.size()==0){
            System.out.println("No Json Files are found!!");
            System.exit(0);
        }
        ArrayList<String[]> tokimon_information = new ArrayList<String[]>();
        for(File x : allFiles){
            test1.ReadJsonFile(x,tokimon_information);
        }
        CsvGenerator outputCsv = new CsvGenerator();
        outputCsv.sort(tokimon_information);
        outputCsv.outputCsv(output,tokimon_information);
    }
}
