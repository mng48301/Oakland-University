import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class MetricConvertorGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // page components
        Button convertButton = new Button("Convert");
        TextField operationInput = new TextField();
        TextField quantityInput = new TextField();
        Label resultLabel = new Label("Result: ");

        operationInput.setPromptText("Enter conversion type (kg/lb, g/oz, km/mi, mm/in)");
        quantityInput.setPromptText("Enter quantity to convert");

        convertButton.setOnAction(e -> {
            double result = convert(operationInput.getText(), Double.parseDouble(quantityInput.getText()));
            resultLabel.setText("Result: " + result);
        });
        

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(operationInput, quantityInput, convertButton, resultLabel);
        Scene scene = new Scene(root, 300, 200);
        
        

        primaryStage.setTitle("Metric Convertor GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        
    }

    public static double convert(String operation, double user_input){
        double computer_output = 0;
        operation = operation.toLowerCase().trim();
        
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

        return computer_output;
    }
}
