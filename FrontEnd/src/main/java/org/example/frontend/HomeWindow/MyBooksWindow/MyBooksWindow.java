package org.example.frontend.HomeWindow.MyBooksWindow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.frontend.Records.BorrowedRecord;
import java.net.URI;
import java.net.http.*;
import java.util.List;

public class MyBooksWindow {

    //URL to backend API
    private static final String BASE_URL = "http://localhost:8080/api/borrowed";

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("View Borrowed Books");

        Button returnButton = new Button("Return");

        returnButton.setOnAction(e -> {
            ReturnWindow returnWindow = new ReturnWindow();
            returnWindow.show();
        });

        //Create table in the view
        TableView<BorrowedRecord> tableView = new TableView<>();

        //Create columns for Borrowed ID, title, author, date
        TableColumn<BorrowedRecord, Integer> borrowedIdColumn = new TableColumn<>("Borrowed ID");
        borrowedIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().borrowedId()).asObject());

        TableColumn<BorrowedRecord, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().title()));

        TableColumn<BorrowedRecord, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().author()));

        TableColumn<BorrowedRecord, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().date_of()));

        //Add the columns to the table
        tableView.getColumns().addAll(borrowedIdColumn, titleColumn, authorColumn, dateColumn);

        //Fetch the data from backend and add it the tableview
        List<BorrowedRecord> borrowed = fetchAllBorrowed();
        tableView.getItems().addAll(borrowed);

        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(returnButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setStyle("-fx-padding: 20;");

        VBox vbox = new VBox(tableView, buttonBox);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);

        stage.show();
    }

    //Method to fetch the books form backend
    public List<BorrowedRecord> fetchAllBorrowed() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/fetch"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response: " + response.body());

            //Creating mapper to mat the response(JSON) to the record
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), new TypeReference<List<BorrowedRecord>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
