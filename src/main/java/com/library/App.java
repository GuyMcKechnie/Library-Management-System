package com.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/view/OnboardingView.fxml"));
            Parent root = loader.load();

            // Create the scene with the loaded FXML
            Scene onboardingScene = new Scene(root);
            onboardingScene.getStylesheets().add(getClass().getResource("/com/library/style.css").toExternalForm());

            // Set the scene on the stage
            primaryStage.setScene(onboardingScene);
            primaryStage.setTitle("Library Manager");
            primaryStage.setResizable(false);
            MainController.setOnboardingStage(primaryStage);

            // Handle window close request
            primaryStage.setOnCloseRequest(event -> {
                System.exit(0);
            });

            // Add the icon
            Image icon = new Image(getClass().getResourceAsStream("/com/library/image/icon.png"));
            primaryStage.getIcons().add(icon);

            // Show the stage
            primaryStage.show();

            // @todo ADDING LOADING BAR
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}