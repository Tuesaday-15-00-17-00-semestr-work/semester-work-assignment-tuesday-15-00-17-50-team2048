package org.example.frontend;

import javafx.geometry.Pos;
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
                // Show an alert if any fields are empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Book Error");
                alert.setHeaderText("Missing Information");
                alert.setContentText("Please fill out all fields.");
                alert.showAndWait();
            } else {
                try {
                    int availableCopies = Integer.parseInt(copies); // Ensure copies is a valid integer
                    if (createBook(title, author, isbn, availableCopies)) {
                        // Show success message and close the window
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Book Added");
                        alert.setContentText("The book has been added successfully!");
                        alert.showAndWait();

                        addStage.close();
                    } else {
                        // Show error if adding the book failed
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Add Book Error");
                        alert.setHeaderText("Failed to Add Book");
                        alert.setContentText("There was an issue adding the book. Please try again.");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException ex) {
                    // Show an error if copies is not a valid number
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Validation Error");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Available Copies must be a valid number.");
                    alert.showAndWait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Show a general error message if something goes wrong
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("An Error Occurred");
                    alert.setContentText("An unexpected error occurred. Please try again.");
                    alert.showAndWait();
                }
            }
        });

        // Arrange components in a VBox
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(
                titleLabel, titleField,
                authorLabel, authorField,
                isbnLabel, isbnField,
                copiesLabel, copiesField,
                addButton
        );
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Create a scene and set it in the stage
        Scene scene = new Scene(layout, 300, 400);
        addStage.setScene(scene);

        // Show the add book stage
        addStage.show();
    }

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