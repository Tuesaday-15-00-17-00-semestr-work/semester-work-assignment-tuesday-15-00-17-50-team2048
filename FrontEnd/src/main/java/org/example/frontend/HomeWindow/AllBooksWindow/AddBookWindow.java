package org.example.frontend.HomeWindow.AllBooksWindow;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URI;
import java.net.http.*;

public class AddBookWindow {

    private static final String CREATE_URL = "http://localhost:8080/api/books/create";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void show(){
        Stage addStage = new Stage();
        addStage.setTitle("Add Book");

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();

        Label copiesLabel = new Label("Available Copies:");
        TextField copiesField = new TextField();

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            String copies = copiesField.getText();

            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || copies.isEmpty()) {
                System.out.println("Error: Please fill out all fields.");
            } else {
                try {
                    //Check is copies is in integer
                    int availableCopies = Integer.parseInt(copies);
                    if (createBook(title, author, isbn, availableCopies)) {
                        // Print success message and close the window
                        System.out.println("Success: The book has been added successfully!");
                        addStage.close();
                    } else {
                        // Print failure message to the console
                        System.out.println("Error: Failed to add book. Please try again.");
                    }
                } catch (NumberFormatException ex) {
                    // Print error if copies is not a valid number
                    System.out.println("Error: Available Copies must be a valid number.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Print a generic error message to the console
                    System.out.println("Error: An unexpected error occurred. Please try again.");
                }
            }
        });

        // Arrange elements in a VBox
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(
                titleLabel, titleField,
                authorLabel, authorField,
                isbnLabel, isbnField,
                copiesLabel, copiesField,
                addButton
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 400);
        addStage.setScene(scene);

        addStage.show();
    }

    //Method to create book by sending POST request
    private boolean createBook(String title, String author, String isbn, int availableCopies) throws Exception {
        // Create book data in JSON format
        String jsonBody = String.format("{\"title\":\"%s\", \"author\":\"%s\", \"isbn\":\"%s\", \"availableCopies\":%d}",
                title, author, isbn, availableCopies);

        // Create an HTTP request to send the data to the server
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CREATE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // Check if the response status code indicates success
        return response.statusCode() == 200;
    }
}