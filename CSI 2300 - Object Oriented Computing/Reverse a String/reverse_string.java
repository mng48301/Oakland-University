
import java.util.Scanner;
public class reverse_string {
    public static void main(String[] args){
        String str, reverse_str = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");

        str = scanner.nextLine();
        scanner.close();
        // reverse string

        for (int i = str.length() - 1; i >= 0; i--){
            reverse_str = reverse_str + str.charAt(i);
        }
        System.out.print("\nReversed string: " + reverse_str);
    }
}
