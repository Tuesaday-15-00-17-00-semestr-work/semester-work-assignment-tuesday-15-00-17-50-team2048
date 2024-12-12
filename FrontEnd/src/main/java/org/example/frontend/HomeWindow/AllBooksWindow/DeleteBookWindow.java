package org.example.frontend.HomeWindow.AllBooksWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class DeleteBookWindow {

    private static final String DELETE_URL = "http://localhost:8080/api/books/delete/{book_id}";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void show(){
        Stage deleteStage = new Stage();
        deleteStage.setTitle("Delete Book");

        Label idLabel = new Label("Enter Book ID:");
        TextField idField = new TextField();
        idField.setPromptText("Book ID");

        Button deleteButton = new Button("Delete");

        deleteButton.setOnAction(e -> {
            String bookId = idField.getText().trim();

            if (!bookId.isEmpty()) {
                System.out.println("Deleting book with ID: " + bookId);

                try {
                    boolean success = deleteBook(bookId);
                    if (success) {
                        System.out.println("Book deleted successfully.");
                    } else {
                        System.out.println("Failed to delete Book.");
                    }
                } catch (IOException | InterruptedException ex) {
                    System.err.println("Error during book deletion: " + ex.getMessage());
                }

                deleteStage.close();
            } else {
                System.out.println("Please enter a valid Book ID.");
            }
        });

        VBox vbox = new VBox(10, idLabel, idField, deleteButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        deleteStage.setScene(scene);

        deleteStage.show();
    }

    private boolean deleteBook(String bookId) throws IOException, InterruptedException {
        // Build the DELETE request URL
        String url = DELETE_URL.replace("{book_id}", bookId);

        // Send DELETE request to delete book entry
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return (response.statusCode() == 200);
    }
}
