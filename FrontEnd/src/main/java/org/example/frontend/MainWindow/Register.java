package org.example.frontend.MainWindow;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.frontend.HomeWindow.Home;

import java.net.http.*;
import java.net.URI;

public class Register {

    //URL to backend API
    private static final String REGISTER_URL = "http://localhost:8080/api/users/create";

    //HTTP Client for sending requests
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final Stage primaryStage;

    //Constructor for primaryStage reference
    public Register(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //Method to display registration window
    public void show() {
        // Create a new stage for the registration screen
        Stage registerStage = new Stage();
        registerStage.setTitle("Register");

        // Create labels and text fields for username, password, and email
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        // Create a register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            //Saving input from fields
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();

            //Check if empty
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                // Show an alert if any fields are empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText("Missing Information");
                alert.setContentText("Please fill out all fields.");
                alert.showAndWait();
            } else {
                try {
                    // If the user is created successfully, proceed
                    if (createUser(username, password, email)) {
                        // Print success message to the console
                        System.out.println("Registration successful! User created.");

                        // Proceed to home screen
                        Home home = new Home(primaryStage);
                        home.show();
                        registerStage.close();
                    } else {
                        // Print failure message to the console
                        System.out.println("Error: Failed to create user. Please try again.");
                    }
                } catch (Exception ex) {
                    // Print the exception and a generic error message
                    ex.printStackTrace();
                    System.out.println("Error: An error occurred while processing your request. Please try again.");
                }
            }
        });

        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                emailLabel, emailField,
                registerButton
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 250);
        registerStage.setScene(scene);

        registerStage.show();
    }

    //Method to create user with POST request
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

        //Checks if the response is positive (succesfull)
        return response.statusCode() == 200;
    }
}
