package org.example.frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViewBooksWindow {

    private static final String BASE_URL = "http://localhost:8080/api/books";

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("View Books");

        Button borrowButton = new Button("Borrow");
        Button addBookButton = new Button("Add Book");
        Button deleteBookButton = new Button("Delete Book");

        borrowButton.setOnAction(e -> {
            BorrowWindow borrowScreen = new BorrowWindow();
            borrowScreen.show();
        });
        addBookButton.setOnAction(e -> {
            AddBookWindow addBookScreen = new AddBookWindow();
            addBookScreen.show();
        });
        deleteBookButton.setOnAction(e -> {
            DeleteBookWindow deleteBookScreen = new DeleteBookWindow();
            deleteBookScreen.show();
        });

        TableView<BookRecord> tableView = new TableView<>();

        TableColumn<BookRecord, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().bookId()).asObject());

        TableColumn<BookRecord, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title()));

        TableColumn<BookRecord, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().author()));

        TableColumn<BookRecord, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isbn()));

        TableColumn<BookRecord, Integer> copiesColumn = new TableColumn<>("Available Copies");
        copiesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().availableCopies()).asObject());

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, isbnColumn, copiesColumn);

        List<BookRecord> books = fetchAllBooks();
        tableView.getItems().addAll(books);

        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(borrowButton, addBookButton, deleteBookButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setStyle("-fx-padding: 20;");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(tableView, buttonBox);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);

        stage.show();
    }

    public List<BookRecord> fetchAllBooks() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/fetch"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response: " + response.body());

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), new TypeReference<List<BookRecord>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
