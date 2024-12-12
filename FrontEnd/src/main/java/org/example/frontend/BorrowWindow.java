package org.example.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class BorrowWindow {

    private static final String BOOK_URL = "http://localhost:8080/api/books/{book_id}";
    private static final String USER_URL = "http://localhost:8080/api/users/{user_id}";
    private static final String TRANS_URL = "http://localhost:8080/api/transactions/create";
    private static final String BORROW_URL = "http://localhost:8080/api/borrowed/create";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void show() {
        Stage borrowStage = new Stage();
        borrowStage.setTitle("Borrow Book");

        Label idLabel = new Label("Enter Book ID:");
        TextField idField = new TextField();
        idField.setPromptText("Book ID");

        Label userLabel = new Label("Enter User ID:");
        TextField userField = new TextField();
        userField.setPromptText("User ID");

        Button borrowButton = new Button("Borrow");

        borrowButton.setOnAction(e -> {
            String bookId = idField.getText().trim();
            String userId = userField.getText().trim();

            if (!bookId.isEmpty()) {
                System.out.println("Borrowing book with ID: " + bookId + " for User ID: " + userId);

                try {
                    boolean success = createTransaction(bookId, userId);
                    if (success) {
                        System.out.println("Transaction created successfully.");
                    } else {
                        System.out.println("Failed to create transaction.");
                    }
                } catch (IOException | InterruptedException ex) {
                    System.err.println("Error during transaction creation: " + ex.getMessage());
                }

                borrowStage.close();
            } else {
                System.out.println("Please enter valid User ID and Book ID.");
            }
        });

        VBox vbox = new VBox(10, userLabel, userField, idLabel, idField, borrowButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        borrowStage.setScene(scene);

        borrowStage.show();
    }

    private boolean createTransaction(String bookId, String userId) throws IOException, InterruptedException {
        // Validate book existence from the database
        if (!resourceExists(BOOK_URL.replace("{book_id}", bookId))) {
            System.out.println("Book with ID " + bookId + " not found.");
            return false;
        }

        // Validate user existence from the database
        if (!resourceExists(USER_URL.replace("{user_id}", userId))) {
            System.out.println("User with ID " + userId + " not found.");
            return false;
        }

        // Fetch book information (title and author) from the API
        String bookResponse = fetchBookInfo(bookId);
        if (bookResponse == null) {
            System.out.println("Failed to fetch book data.");
            return false;
        }

        // Parse the book information to extract title and author
        String[] bookInfo = parseBookResponse(bookResponse);
        if (bookInfo == null) {
            System.out.println("Failed to parse book data.");
            return false;
        }

        String title = bookInfo[0];
        String author = bookInfo[1];

        // Create a borrowed entry with the book details
        createBorrowedEntry(title, author, "12.12.2024");  // Pass the date of borrowing (or use the current date)


        // Prepare transaction data
        String jsonBody = String.format(
                "{\"user_id\": \"%s\", \"book_id\": \"%s\", \"actions\": \"BORROW\", \"date_of\": \"12.12.2024\"}",
                userId, bookId
        );

        // Send POST request to create transaction
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TRANS_URL))
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

    private boolean resourceExists(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        return response.statusCode() == 200; // Assuming 200 OK means the resource exists
    }

    private String fetchBookInfo(String bookId) throws IOException, InterruptedException {
        // Send GET request to fetch book data from the API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BOOK_URL.replace("{book_id}", bookId)))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            System.out.println("Failed to fetch book data.");
            return null;
        }
    }

    private String[] parseBookResponse(String response) {
        try {
            // Parse the response to extract title and author
            response = response.replaceAll("^\\{|\\}$", ""); // Clean braces
            String[] fields = response.split(",");
            String title = fields[1].split(":")[1].replace("\"", "").trim();
            String author = fields[2].split(":")[1].replace("\"", "").trim();
            return new String[]{title, author};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createBorrowedEntry(String title, String author, String dateOf) throws IOException, InterruptedException {
        String jsonBody = String.format(
                "{\"title\": \"%s\", \"author\": \"%s\", \"date_of\": \"%s\"}",
                title, author, dateOf
        );

        // Send POST request to create borrowed entry
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BORROW_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            System.out.println("Borrowed entry created successfully.");
        } else {
            System.out.println("Failed to create borrowed entry. Status: " + response.statusCode());
        }
    }
}
