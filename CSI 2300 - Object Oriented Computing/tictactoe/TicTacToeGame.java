package tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeGame extends Application {
    // Game logic properties
    private String[][] board;
    private String currentPlayerMark;
    private int boardSize;
    private final int WIN_COUNT = 5; // Number in a row needed to win
    
    // UI components
    private Button[][] buttons;
    private Label statusLabel;
    private static final int BOARD_SIZE = 5;
    private static final int BUTTON_SIZE = 60;
    
    // Constructor
    public TicTacToeGame() {
        // Default constructor for JavaFX
    }
    
    public TicTacToeGame(int boardSize) {
        this.boardSize = boardSize;
        board = new String[boardSize][boardSize];
        resetGame();
    }
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize game logic
        boardSize = BOARD_SIZE;
        board = new String[boardSize][boardSize];
        resetGame();
        
        // Initialize UI
        buttons = new Button[BOARD_SIZE][BOARD_SIZE];
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Status label at the top
        statusLabel = new Label("Player X's turn");
        statusLabel.setFont(Font.font(16));
        HBox topPane = new HBox(statusLabel);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(10));
        root.setTop(topPane);
        
        // Game board in the center
        GridPane gridPane = createGameBoard();
        root.setCenter(gridPane);
        
        // Reset button at the bottom
        Button resetButton = new Button("New Game");
        resetButton.setFont(Font.font(14));
        resetButton.setOnAction(e -> resetGame());
        HBox bottomPane = new HBox(resetButton);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(10));
        root.setBottom(bottomPane);
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("5x5 Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    // UI Methods
    private GridPane createGameBoard() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = createGameButton(row, col);
                gridPane.add(button, col, row);
                buttons[row][col] = button;
            }
        }
        
        return gridPane;
    }
    
    private Button createGameButton(int row, int col) {
        Button button = new Button();
        button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        button.setFont(Font.font(18));
        button.setOnAction(e -> handleButtonClick(row, col));
        return button;
    }
    
    private void handleButtonClick(int row, int col) {
        if (makeMove(row, col)) {
            updateButton(row, col);
            
            if (checkWinner()) {
                statusLabel.setText("Player " + currentPlayerMark + " wins!");
                disableAllButtons();
            } else if (isBoardFull()) {
                statusLabel.setText("Game ended in a draw!");
            } else {
                switchPlayer();
                statusLabel.setText("Player " + currentPlayerMark + "'s turn");
            }
        }
    }
    
    private void updateButton(int row, int col) {
        buttons[row][col].setText(getBoardValue(row, col));
        buttons[row][col].setDisable(true);
    }
    
    private void disableAllButtons() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }
    
    private void updateUI() {
        statusLabel.setText("Player " + currentPlayerMark + "'s turn");
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setDisable(false);
            }
        }
    }
    
    // Game Logic Methods
    public void resetGame() {
        // we initialize the board with empty cells. 
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = "";
            }
        }
        currentPlayerMark = "X"; // X always starts
        
        // Update UI if it's initialized
        if (buttons != null) {
            updateUI();
        }
    }
    
    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col].equals("")) {
            board[row][col] = currentPlayerMark;
            return true;
        }
        return false;
    }
    
    public boolean checkWinner() {
        // Check rows
        for (int row = 0; row < boardSize; row++) {
            int count = 0;
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col].equals(currentPlayerMark)) {
                    count++;
                    if (count >= WIN_COUNT) return true;
                } else {
                    count = 0;
                }
            }
        }
        
        // Check columns
        for (int col = 0; col < boardSize; col++) {
            int count = 0;
            for (int row = 0; row < boardSize; row++) {
                if (board[row][col].equals(currentPlayerMark)) {
                    count++;
                    if (count >= WIN_COUNT) return true;
                } else {
                    count = 0;
                }
            }
        }
        
        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row <= boardSize - WIN_COUNT; row++) {
            for (int col = 0; col <= boardSize - WIN_COUNT; col++) {
                boolean win = true;
                for (int i = 0; i < WIN_COUNT; i++) {
                    if (!board[row + i][col + i].equals(currentPlayerMark)) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        
        // Check diagonal (top-right to bottom-left)
        for (int row = 0; row <= boardSize - WIN_COUNT; row++) {
            for (int col = WIN_COUNT - 1; col < boardSize; col++) {
                boolean win = true;
                for (int i = 0; i < WIN_COUNT; i++) {
                    if (!board[row + i][col - i].equals(currentPlayerMark)) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        
        return false;
    }
    
    public boolean isBoardFull() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void switchPlayer() {
        currentPlayerMark = (currentPlayerMark.equals("X")) ? "O" : "X";
    }
    
    public String getCurrentPlayerMark() {
        return currentPlayerMark;
    }
    
    public String getBoardValue(int row, int col) {
        if (row >= 0 && row < boardSize && col >= 0 && col < boardSize) {
            return board[row][col];
        }
        return "";
    }
    
    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
