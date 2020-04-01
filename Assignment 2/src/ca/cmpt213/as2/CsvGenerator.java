package ca.cmpt213.as2;
import com.opencsv.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class is responsible for outputtig the .csv file
 * This class implements a sort function.
 */

public class CsvGenerator {
    public void outputCsv (File file, ArrayList<String[]> tokimon_information) throws IOException {

        FileWriter outputFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputFile);
        String[] header = {"Team#", "From Toki", "To Toki","","Score","Comment","","Extra"};
        writer.writeNext(header);
        String[] teamNumber={"Team1"};
        writer.writeNext(teamNumber);
        for(String[] s: tokimon_information){
            writer.writeNext(s);
        }
        writer.close();
    }
    public void sort (ArrayList<String[]> tokimon_information){
        Comparator<String[]> sortByTeam = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int result;
                result=(o1[1].substring(0,2).compareTo(o2[1].substring(0,2)));
                if (result >  0){
                    String[] temp= o1;
                    o1=o2;
                    o2=temp;
                }
                return 0;
            }
        };
    }
}
