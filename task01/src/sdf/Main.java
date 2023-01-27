package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        // get file name and check if string argument is correct 
        if (!(args.length == 1)) {
            System.err.println("Please only enter 1 text file name to be processed");
            System.exit(1);
        } 
        File fileName = new File("./src/sdf/files/" + args[0]);  

        // check if file exists. If it doesnt, early exit the program 
        if (!fileName.exists()) {
            System.err.println("The text file does not exist. Please check."); 
            System.exit(1);  
        }

        // Read file to list and split the words all the way into a list of individual words 
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr); 
        List<String> lines = new ArrayList<>();
        List<String> allWords = new ArrayList<>(); 

        lines = br.lines().toList(); 
        System.out.println(lines);
        String[] words = null; 

        for (String line : lines) {
            words = line.trim().split(" ");
            for (String word : words) {
                allWords.add(word); 
            }
        }
        System.out.println(allWords);

        
        
        
    }
}
