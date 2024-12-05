package org.example.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label login;

    @FXML
    protected void onLoginButtonClick() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent mainViewRoot = fxmlLoader.load();

        // Create a new Stage for the main window
        Stage stage = new Stage();
        stage.setTitle("Main Window");
        stage.setScene(new Scene(mainViewRoot, 800, 600));
        stage.show();

        // Close the login window
        Stage loginStage = (Stage) login.getScene().getWindow();
        loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}