package edu.utsa.cs3443.uzd052_lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Aid Ship Management System
 * Launches the login screen on startup
 * 
 * @author uzd052
 */
public class AidShipApplication extends Application {
    
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(
            AidShipApplication.class.getResource("layouts/login-screen.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);

        stage.setTitle("GERO Aid Ship Management System");
        stage.setMinWidth(500);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets the primary stage
     * 
     * @return the primary stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Main method to launch the application
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
