package org.example.frontend.HomeWindow.UsersWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteUserWindow {

    private static final String DELETE_URL = "http://localhost:8080/api/users/delete/{user_id}";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void show() {

        Stage deleteStage = new Stage();
        deleteStage.setTitle("Delete User");

        Label idLabel = new Label("Enter User ID:");
        TextField idField = new TextField();
        idField.setPromptText("User ID");

        Button deleteButton = new Button("Delete");

        deleteButton.setOnAction(e -> {
            String userId = idField.getText().trim();

            if (!userId.isEmpty()) {
                System.out.println("Deleting user with ID: " + userId);

                try {
                    boolean success = deleteUser(userId);
                    if (success) {
                        System.out.println("User deleted successfully.");
                    } else {
                        System.out.println("Failed to delete User.");
                    }
                } catch (IOException | InterruptedException ex) {
                    System.err.println("Error during user deletion: " + ex.getMessage());
                }

                deleteStage.close();
            } else {
                System.out.println("Please enter a valid User ID.");
            }
        });

        VBox vbox = new VBox(10, idLabel, idField, deleteButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        deleteStage.setScene(scene);

        deleteStage.show();
    }

    private boolean deleteUser(String userId) throws IOException, InterruptedException {
        // Build the DELETE request URL
        String url = DELETE_URL.replace("{user_id}", userId);

        // Send DELETE request to delete the borrowed entry
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("User with User ID " + userId + " deleted successfully.");
            return true;
        } else {
            System.out.println("Failed to delete user. Status: " + response.statusCode());
            return false;
        }
    }
}
