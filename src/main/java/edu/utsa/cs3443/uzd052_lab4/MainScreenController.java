package edu.utsa.cs3443.uzd052_lab4;

import edu.utsa.cs3443.uzd052_lab4.model.AidShip;
import edu.utsa.cs3443.uzd052_lab4.model.AidShipManager;
import edu.utsa.cs3443.uzd052_lab4.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.Comparator;

import java.io.IOException;

/**
 * Controller for the main screen.
 * Displays and manages the aid ship list with sorting and logout functionality.
 * 
 * @author uzd052
 */
public class MainScreenController {
    
    @FXML
    private Label welcomeLabel;
    
    @FXML
    private ListView<String> shipListView;
    
    @FXML
    private RadioButton sortByNameRadio;
    
    @FXML
    private RadioButton sortByRegNumberRadio;
    
    @FXML
    private ToggleGroup sortToggleGroup;
    
    private AidShipManager shipManager;

    /**
     * Initializes the controller.
     * Loads ship data and sets up the UI.
     */
    @FXML
    public void initialize() {
        shipManager = new AidShipManager();
        
        // Set welcome message
        User currentUser = LoginScreenController.getCurrentUser();
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName());
        }
        
        // Load ships
        try {
            // Use the existing API on AidShipManager to load ships from CSV
            shipManager.loadAidShips();
            displayShips();
        } catch (IOException e) {
            showAlert("Error", "Failed to load ship data: " + e.getMessage());
        }
        
        // Set up radio button toggle group
        sortToggleGroup = new ToggleGroup();
        sortByNameRadio.setToggleGroup(sortToggleGroup);
        sortByRegNumberRadio.setToggleGroup(sortToggleGroup);
        sortByNameRadio.setSelected(true);
        
        // Add listeners for sorting
        sortToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                sortShips();
            }
        });
    }

    /**
     * Displays the ships in the list view.
     */
    private void displayShips() {
        shipListView.getItems().clear();
        for (AidShip ship : shipManager.getAidShipList()) {
            shipListView.getItems().add(ship.toString());
        }
    }

    /**
     * Sorts the ships based on the selected radio button.
     */
    private void sortShips() {
        if (sortByNameRadio.isSelected()) {
            Collections.sort(
                shipManager.getAidShipList(),
                Comparator.comparing(a -> a.getName() == null ? "" : a.getName(), String.CASE_INSENSITIVE_ORDER)
            );
        } else if (sortByRegNumberRadio.isSelected()) {
            Collections.sort(
                shipManager.getAidShipList(),
                Comparator.comparing(a -> a.getRegistrationNumber() == null ? "" : a.getRegistrationNumber(), String.CASE_INSENSITIVE_ORDER)
            );
        }
        displayShips();
    }

    /**
     * Handles the sort button action.
     */
    @FXML
    protected void onSortButtonClick() {
        sortShips();
    }

    /**
     * Handles the logout button action.
     * Clears the session and returns to the login screen.
     */
    @FXML
    protected void onLogoutButtonClick() {
        try {
            LoginScreenController.clearSession();
            loadLoginScreen();
        } catch (IOException e) {
            showAlert("Error", "Failed to logout: " + e.getMessage());
        }
    }

    /**
     * Loads the login screen.
     * 
     * @throws IOException if there's an error loading the FXML
     */
    private void loadLoginScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            AidShipApplication.class.getResource("layouts/login-screen.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        
        Stage stage = AidShipApplication.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("GERO Aid Ship Management System");
    }

    /**
     * Displays an alert dialog.
     * 
     * @param title the alert title
     * @param message the alert message
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
