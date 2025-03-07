import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class BubbleSort {
    
    public static int[] createRandomArray(int arrayLength){
        int[] array = new int[arrayLength];
    
        for (int i = 0; i < arrayLength; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
    public static void writeArrayToFile(int[] array, String filename){
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < array.length; i++){
                writer.write(array[i] + "\n");
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
               
    }
     
    public static int[] readFileToArray(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArrayList<Integer> list = new ArrayList<>();
        String line;
        
        while ((line = reader.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }
        reader.close();
        
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    public static void bubbleSort(int[] array){
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++){
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                
            }
            if (swapped == false) {
                break;
            }
        }
        //array is now sorted
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("To create and sort new array: java -jar BubbleSort.jar <array_length>");
            System.out.println("To sort existing file: java -jar BubbleSort.jar <input_filename>");
            System.exit(1);
        }

        try {
            // Try to parse as integer for array generation
            try {
                int arrayLength = Integer.parseInt(args[0]);
                if (arrayLength <= 0) {
                    System.out.println("Array length must be positive");
                    System.exit(1);
                }
                
                int[] array = createRandomArray(arrayLength);
                writeArrayToFile(array, "array.txt");
                bubbleSort(array);
                writeArrayToFile(array, "sorted_array.txt");
                System.out.println("Created and sorted array of length " + arrayLength);
            } 
            // If not an integer, treat as filename
            catch (NumberFormatException e) {
                String inputFile = args[0];
                int[] array = readFileToArray(inputFile);
                bubbleSort(array);
                writeArrayToFile(array, "sorted_" + inputFile);
                System.out.println("Sorted contents of " + inputFile);
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
     
}
