package countwords;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("No valid input.");
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        HashMap<String, Integer> wordCount = new HashMap<>();
        try {
            java.util.Scanner input = new java.util.Scanner(file);
            
            while (input.hasNext()) {
                String word = input.next();
                if (word != null && !word.isEmpty()) {
                    if (wordCount.containsKey(word)) {
                        wordCount.put(word, wordCount.get(word) + 1);
                    }
                    else {
                        wordCount.put(word, 1);
                    }
                }
            }
            input.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        try {
            java.io.PrintWriter output = new java.io.PrintWriter("output.txt");
            for (String word : wordCount.keySet()) {
                output.println(word + ": " + wordCount.get(word));
            }
            output.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
