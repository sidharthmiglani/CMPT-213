package ca.cmpt213.as2;
import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for Reading
 * the JSON file and storing them into
 * an Arraylist consisting of String arrays.
 * -> This class implements a recursive function that finds the JSON file.
 **/

public class ReadJson {
    String name,from,to,score,comment,extra_comments;
    String teamLead;
    public void ReadJsonFile(File file, ArrayList<String[]> tokimon_information) throws FileNotFoundException{
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(new FileReader(file));
        JsonArray arr = (JsonArray) obj.get("team");
        extra_comments = obj.get("extra_comments").getAsString();
        int count=0;
        for (Object toki : arr){
            JsonObject tokimon = (JsonObject) toki;
            name = tokimon.get("name").getAsString();
            if (count==0){
                from = tokimon.get("id").getAsString();
                teamLead = tokimon.get("id").getAsString();
                to = "-";
            }
            else{
                from =teamLead;
                to = tokimon.get("id").getAsString();
                extra_comments="";
            }
            JsonObject compatibility = (JsonObject) tokimon.get("compatibility");
            score = compatibility.get("score").getAsString();
            comment = compatibility.get("comment").getAsString();
            tokimon_information.add(new String[]{"",from,to,"",score,comment,"",extra_comments});
            count++;
        }
    }



    public File findJsonRecursively (File file,ArrayList<File> jsonFiles) {
        if (file.isDirectory()) {
            File[] newFiles = file.listFiles();
            for (File test : newFiles) {
                File found = findJsonRecursively(test,jsonFiles);
                if (test.getAbsolutePath().toLowerCase().endsWith("json")) {
                    jsonFiles.add(found);
                    return found;
                }
            }
        }
        else {
            if(file.getAbsolutePath().toLowerCase().endsWith("json")){
                return file;
            }
        }
        return null;
    }
}
