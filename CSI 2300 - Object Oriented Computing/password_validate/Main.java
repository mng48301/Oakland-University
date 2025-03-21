package password_validate;

public class Main {
    
    public static void main(String[] args){
        
        // length check
        if (args.length == 0) {
            System.out.println("No password provided");
            return;
        }
        String password = args[0];
        if (password.length() <= 16 && password.length() >= 8) {
            System.out.println("Password length is correct");
        } else {
            System.out.println("Password length is incorrect");
        }

        int requirements = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches(".*[a-z].*")) {
                requirements++;
                break;
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches(".*[A-Z].*")) {
                requirements++;
                break;
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches(".*[0-9].*")) {
                requirements++;
                break;
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches(".*[!@#$%^&*()].*")) {
                requirements++;
                break;
            }
        }

        if (requirements >= 3) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is invalid");
        }
    }
}
