package org.example.frontend;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.http.*;
import java.net.URI;

public class Register {

    private static final String REGISTER_URL = "http://localhost:8080/api/users/create";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final Stage primaryStage; // Reference to the primary stage

    public Register(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        // Create a new stage for the registration screen
        Stage registerStage = new Stage();
        registerStage.setTitle("Register");

        // Create fields for username, password, and email
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        // Create a register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                // Show an alert if any fields are empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText("Missing Information");
                alert.setContentText("Please fill out all fields.");
                alert.showAndWait();
            } else {
                // Create the user in the backend
                try {
                    if (createUser(username, password, email)) {
                        // Show success message and navigate to the home screen
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Registration Successful");
                        alert.setHeaderText("User Created");
                        alert.setContentText("You have successfully registered!");
                        alert.showAndWait();

                        // Navigate to home screen and close the registration stage
                        Home home = new Home(primaryStage);
                        home.show();
                        registerStage.close();
                    } else {
                        // Show error if the user creation failed
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Registration Error");
                        alert.setHeaderText("Failed to Create User");
                        alert.setContentText("There was an issue creating your account. Please try again.");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Show an error message if there is an exception
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Registration Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("An error occurred while processing your request. Please try again.");
                    alert.showAndWait();
                }
            }
        });

        // Arrange components in a VBox
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                emailLabel, emailField,
                registerButton
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Create a scene and set it in the stage
        Scene scene = new Scene(layout, 300, 250);
        registerStage.setScene(scene);

        // Show the registration stage
        registerStage.show();
    }

    private boolean createUser(String username, String password, String email) throws Exception {
        // Create user data in JSON format
        String jsonBody = String.format("{\"username\":\"%s\", \"password\":\"%s\", \"role_id\":2, \"email\":\"%s\"}",
                username, password, email);

        // Create an HTTP client to send the request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(REGISTER_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send the POST request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response status code is 200
        return response.statusCode() == 200;
    }
}
