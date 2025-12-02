package edu.utsa.cs3443.uzd052_lab4;

import edu.utsa.cs3443.uzd052_lab4.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the login screen
 * Handles user authentication and navigation to the main screen
 * 
 * @author uzd052
 */
public class LoginScreenController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    private static User currentUser;

    /**
     * Initializes the controller
     */
    @FXML
    public void initialize() {
        errorLabel.setVisible(false);
    }

    /**
     * Handles the login button action
     * Validates credentials and navigates to main screen if successful
     */
    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // Validate input
        if (username == null || username.trim().isEmpty()) {
            showError("Username cannot be empty");
            return;
        }
        
        if (password == null || password.trim().isEmpty()) {
            showError("Password cannot be empty");
            return;
        }
        
        try {
            User user = User.authenticate(username, password);
            
            if (user != null) {
                currentUser = user;
                loadMainScreen();
            } else {
                showError("Invalid username or password");
            }
        } catch (IOException e) {
            showError("Error reading user data: " + e.getMessage());
        }
    }

    /**
     * Displays an error message
     * 
     * @param message the error message to display
     */
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    /**
     * Loads the main screen
     * 
     * @throws IOException if there's an error loading the FXML
     */
    private void loadMainScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            AidShipApplication.class.getResource("layouts/main-screen.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        
        Stage stage = AidShipApplication.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("GERO Aid Ship Management - Welcome " + currentUser.getFirstName());
    }

    /**
     * Gets the currently logged in user
     * 
     * @return the current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the current user session
     */
    public static void clearSession() {
        currentUser = null;
    }
}
