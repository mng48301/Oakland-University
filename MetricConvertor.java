import java.util.Scanner;

public class MetricConvertor {
    public static void main(String[] args) throws Exception {
        String operation, decision;
        double user_input, computer_output = 0;
        boolean running = true;

        
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Metric Convertor ---");
        System.out.println(">exit");
        System.out.println(">convert");
        decision = scanner.nextLine();
        if (decision.equals("exit")){
            System.exit(0);
        }
        if (decision.equals("convert")){
            running = true;
        }

        while (running){
            System.out.println("Please input your conversion type: kg to lb (kg/lb), gram to ounces(g/oz), km to mile(km/mi), mm to inch(mm/in) or type 'exit' to quit");
            operation = scanner.nextLine();

            while (!operation.equals("kg/lb") && !operation.equals("g/oz") && !operation.equals("km/mi") && !operation.equals("mm/in") && !operation.equals("exit")) {
                System.out.println("Operation must be in valid format as the examples shown. Please input again");

                operation = scanner.nextLine();
            }
            
            if (operation.equals("exit")) {
                running = false;
                break;
            }

            System.out.println("Enter the input: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a numeric value: ");
                scanner.next(); // clear the invalid input
            }
            user_input = scanner.nextDouble();
            scanner.nextLine();
            if (operation.equals("kg/lb")) {
                computer_output = user_input * 2.2;
            }
            if (operation.equals("g/oz")) {
                computer_output = user_input / 28.34952;
            }
            if (operation.equals("km/mi")) {
                computer_output = user_input * 0.62137;
            }
            if (operation.equals("mm/in")) {
                computer_output = user_input / 25.4;
            }

            System.out.println("Your output is " + computer_output);
        }
        scanner.close();
    }
}
