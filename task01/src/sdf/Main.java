package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // #1 get file name and check if string argument is correct 
        if (!(args.length == 1)) {
            System.err.println("Please enter 1 text file name to be processed");
            System.exit(1);
        } 
        File fileName = new File("./src/sdf/files/" + args[0]);  

        // check if file exists. If it doesnt, early exit the program 
        if (!fileName.exists()) {
            System.err.println("The text file does not exist. Please check."); 
            System.exit(1);  
        }

        // #2 Read file to list and split the words all the way into a list of individual words 
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr); 
        List<String> lines = new ArrayList<>();
        List<String> allWords = new ArrayList<>(); 

        lines = br.lines().filter(line->!line.isEmpty()).toList(); 
        br.close();
        //System.out.println(lines);
        String[] words = null; 

        for (String line : lines) {
            words = line.trim().toLowerCase().split(" ");
            for (String word : words) {
                allWords.add(word); 
            }
        }
        // System.out.println(allWords);

        // #3 remove punctuations in each words
        for (int i = 0; i < allWords.size(); i++) {
            allWords.set(i, allWords.get(i).replaceAll("[^a-zA-Z0-9\\s+]", "")); 
        }
        // System.out.println(allWords);

        // #4 check the words against hashmap and tabulate total words 
        HashMap<String, Double> wordsMap = new HashMap<>();
        double sum = allWords.size(); 
        // System.out.println("Total word count in this text: " + sum);

        for (String each : allWords) {
            if (!wordsMap.containsKey(each)) {
                wordsMap.put(each, 0.0d); 
            }
            wordsMap.put(each, wordsMap.get(each) + 1);
        }
        //System.out.println(wordsMap);

        // #5 use the hashmap to calculate term frequency for all unique words and replace the value 
        Double freq = 0.0d; 
        for (String each : wordsMap.keySet()) {
            freq = wordsMap.get(each) / sum; 
            wordsMap.put(each, freq); 
        }
        // System.out.println(wordsMap);

        // #6 sort the hashmap according to highest freq in a new linkedhashmap 
        List<Map.Entry<String, Double>> list = new ArrayList<>(wordsMap.entrySet()); 
        list.sort(Collections.reverseOrder(Comparator.comparingDouble(Map.Entry::getValue)));

        HashMap<String, Double> sortedMap = new LinkedHashMap<>(); // the order of keying matters 
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // #7 print out the top 10 using iterator 
        Set<Map.Entry<String, Double>> entries = sortedMap.entrySet(); 
        Iterator<Map.Entry<String, Double>> iterator = entries.iterator(); 
        
        int count = 0; 
        DecimalFormat df = new DecimalFormat("#.###"); 
        System.out.println();
        System.out.println("The top 10 words with the highest frequency are:");
        while (iterator.hasNext() && count < 10) {
            Map.Entry<String, Double> entry = iterator.next(); 
            System.out.println("Word " + String.valueOf(count+1)  + ": \"" + entry.getKey() + "\", " + "Term-frequency: " + df.format(entry.getValue()));
            count ++;
        }
    }
}
