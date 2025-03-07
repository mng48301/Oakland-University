package fibonacci;

public class Fibonacci{

    public static void main(String args[]) {
        
        try {
            int n = Integer.parseInt(args[0]);
            if (n < 0) {
                System.out.println("Please enter a positive number");
            }
            else {
                System.out.print(compute_fib(n));
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        }
    }

    public static int compute_fib(int n){

        int num1, num2, sum;

        num1 = 0;
        num2 = 1;

        if (n == 0) {
            return num1;
        }
        else if (n == 1) {
            return num2;
        }
        else {
            for (int i = 2; i <= n; i++){
                sum = num1 + num2;
                num1 = num2;
                num2 = sum;
            }
            return num2;
        }
    }
    
}