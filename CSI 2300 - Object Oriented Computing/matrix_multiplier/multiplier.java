import java.util.*;
import java.io.*;


public class multiplier {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        if (args.length == 2){
            //command line arguments used
            File file1 = new File(args[0]);
            File file2 = new File(args[1]);
            if (file1.exists() && file2.exists()){
                int[][] matrix_1 = readMatrixFromFile(args[0]);
                int[][] matrix_2 = readMatrixFromFile(args[1]);
                int[][] result = multiplyMatrices(matrix_1, matrix_2);
                writeMatrixToFile(result, "matrix3.txt");
            }

        }  
        System.out.println("Enter two file names or an integer: ");
        if (scanner.hasNextInt()){
            // an integer is entered
            int size = scanner.nextInt();
            int[][] matrix_1 = generateRandomMatrix(size);
            int[][] matrix_2 = generateRandomMatrix(size);
            writeMatrixToFile(matrix_1, "matrix1.txt");
            writeMatrixToFile(matrix_2, "matrix2.txt");
            int[][] result = multiplyMatrices(matrix_1, matrix_2);
            writeMatrixToFile(result, "matrix3.txt");

        }
        else{
            // two file names are entered
            String filename1 = scanner.next();
            String filename2 = scanner.next();
            int[][] matrix_1 = readMatrixFromFile(filename1);
            int[][] matrix_2 = readMatrixFromFile(filename2);
            int[][] result = multiplyMatrices(matrix_1, matrix_2);
            writeMatrixToFile(result, "matrix3.txt");
        }
        
        scanner.close();
    }

    private static int[][] generateRandomMatrix(int size){
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++){
            for (int m = 0; m < size; m++){
                matrix[i][m] = rand.nextInt(10);
            }
        }
        return matrix;
    }
    private static int[][] readMatrixFromFile(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArrayList<int[]> matrix = new ArrayList<int[]>();
        String line;
        
        while((line = reader.readLine()) != null){
            String[] portion = line.trim().split(" ");
            int[] row = new int[portion.length];
            for (int i = 0; i < portion.length; i++){
                row[i] = Integer.parseInt(portion[i]);
            }
            matrix.add(row);
        }
        reader.close();
        return matrix.toArray(new int[0][]);
    }
    private static void writeMatrixToFile(int[][] matrix, String filename) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int[] row : matrix){
            for (int num : row){
                writer.write(num + " ");
            }
            writer.newLine();
        }
        writer.close();

        }
    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2){
        
        int rows_1 = matrix1.length, cols_1 = matrix1[0].length;
        int rows_2 = matrix2.length, cols_2 = matrix2[0].length;
        int[][]result = new int[rows_1][cols_2];
        if (cols_1 != rows_2){
            System.out.println("The matrices cannot be multiplied");
            return null;
        }

        for (int r = 0; r < rows_1; r++){ // r represents matrix1 row
            for (int c = 0; c < cols_2; c++){ // c represents matrix2 column 
                for (int i = 0; i < cols_1; i++){ // i serves as the incrementor (cols_1 is just the common dimension)
                
                    result[r][c] += (matrix1[r][i] * matrix2[i][c]); 
                }
            
            }
        }
        return result;

    }
    }

   