package org.example.frontend.HomeWindow.UsersWindow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.frontend.Records.UserRecord;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserWindow {

    private static final String BASE_URL = "http://localhost:8080/api/users";

    private final HttpClient httpClient = HttpClient.newHttpClient();


    public void show() {
        Stage userStage = new Stage();
        userStage.setTitle("Users");

        Button deleteButton = new Button("Delete");

        deleteButton.setOnAction(e -> {
            DeleteUserWindow deleteUserWindow = new DeleteUserWindow();
            deleteUserWindow.show();
        });

        TableView<UserRecord> tableView = new TableView<>();

        TableColumn<UserRecord, Integer> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().userId()).asObject());

        TableColumn<UserRecord, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().username()));

        TableColumn<UserRecord, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));

        TableColumn<UserRecord, Integer> roleIdColumn = new TableColumn<>("Role ID");
        roleIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().roleId()).asObject());


        tableView.getColumns().addAll(userIdColumn, usernameColumn, emailColumn, roleIdColumn);

        List<UserRecord> users = fetchAllUsers();
        tableView.getItems().addAll(users);

        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(deleteButton, tableView);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setStyle("-fx-padding: 20;");

        VBox vbox = new VBox(tableView, buttonBox);
        Scene scene = new Scene(vbox, 800, 600);
        userStage.setScene(scene);

        userStage.show();
    }

    public List<UserRecord> fetchAllUsers() {
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
            return mapper.readValue(response.body(), new TypeReference<List<UserRecord>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
