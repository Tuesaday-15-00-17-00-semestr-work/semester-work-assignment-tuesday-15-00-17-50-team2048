package org.example.frontend;

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

public class ReturnWindow {

    private static final String RETURN_URL = "http://localhost:8080/api/borrowed/delete/{borrow_id}";
    private static final String CREATE_URL = "http://localhost:8080/api/transactions/create";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void show() {
        Stage returnStage = new Stage();
        returnStage.setTitle("Return Book");

        Label idLabel = new Label("Enter Borrowed ID:");
        TextField idField = new TextField();
        idField.setPromptText("Borrowed ID");

        Label userLabel = new Label("Enter User ID:");
        TextField userField = new TextField();
        userField.setPromptText("User ID");

        Button returnButton = new Button("Return");

        returnButton.setOnAction(e -> {
            String borrowId = idField.getText().trim();
            String userId = userField.getText().trim();

            if (!borrowId.isEmpty()) {
                System.out.println("Returning book with Borrowed ID: " + borrowId);

                try {
                    // Perform book return and create a transaction
                    boolean returnSuccess = returnBook(borrowId);
                    if (returnSuccess) {
                        boolean transactionSuccess = createTransaction(borrowId, userId);
                        if (transactionSuccess) {
                            System.out.println("Book returned and transaction created successfully.");
                        } else {
                            System.out.println("Failed to create transaction.");
                        }
                    } else {
                        System.out.println("Failed to return book.");
                    }
                } catch (IOException | InterruptedException ex) {
                    System.err.println("Error during book return: " + ex.getMessage());
                }

                returnStage.close(); // Close the window after processing
            }});

        VBox vbox = new VBox(10, idLabel, idField, userLabel, userField, returnButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        returnStage.setScene(scene);

        returnStage.show();
    }

    private boolean returnBook(String borrowId) throws IOException, InterruptedException {
        // Build the DELETE request URL
        String url = RETURN_URL.replace("{borrow_id}", borrowId);

        // Send DELETE request to delete the borrowed entry
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Book with Borrow ID " + borrowId + " returned successfully.");
            return true;
        } else {
            System.out.println("Failed to return book. Status: " + response.statusCode());
            return false;
        }
    }

    private boolean createTransaction(String borrowId, String userId) throws IOException, InterruptedException {
        // Prepare transaction data
        String jsonBody = String.format(
                "{\"user_id\": \"%s\", \"borrow_id\": \"%s\", \"actions\": \"RETURN\", \"date_of\": \"12.12.2024\"}",
                userId, borrowId
        );

        // Send POST request to create transaction
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CREATE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Transaction created successfully.");
            return true;
        } else {
            System.out.println("Failed to create transaction. Status: " + response.statusCode());
            return false;
        }
    }



}

