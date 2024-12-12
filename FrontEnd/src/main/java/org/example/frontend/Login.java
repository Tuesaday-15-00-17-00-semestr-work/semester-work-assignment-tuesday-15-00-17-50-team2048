package org.example.frontend;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login {

    private final Stage primaryStage;

    public Login(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Missing Credentials");
                alert.setContentText("Please enter both username and password.");
                alert.showAndWait();
            } else {
                Home home = new Home(primaryStage);
                home.show();
                loginStage.close();
            }
        });

        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        loginStage.setScene(scene);

        loginStage.show();
    }
}
