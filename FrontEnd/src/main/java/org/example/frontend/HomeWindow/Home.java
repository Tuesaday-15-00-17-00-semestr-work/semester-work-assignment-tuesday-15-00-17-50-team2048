package org.example.frontend.HomeWindow;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.frontend.HomeWindow.MyBooksWindow.MyBooksWindow;
import org.example.frontend.HomeWindow.UsersWindow.UserWindow;
import org.example.frontend.HomeWindow.AllBooksWindow.ViewBooksWindow;

public class Home {

    private final Stage primaryStage;

    public Home(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        BorderPane layout = new BorderPane();

        Button booksButton = new Button("Users");
        Button myBooksButton = new Button("My Books");
        Button viewBooksButton = new Button("View Books");

        booksButton.setOnAction(e -> {
            UserWindow booksScreen = new UserWindow();
            booksScreen.show();
        });

        myBooksButton.setOnAction(e -> {
            MyBooksWindow myBooksScreen = new MyBooksWindow();
            myBooksScreen.show();
        });

        viewBooksButton.setOnAction(e -> {
            ViewBooksWindow viewBooksWindow = new ViewBooksWindow();
            viewBooksWindow.show();
        });

        booksButton.setPrefSize(150, 50);
        myBooksButton.setPrefSize(150, 50);
        viewBooksButton.setPrefSize(150, 50);

        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(booksButton, myBooksButton, viewBooksButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setStyle("-fx-padding: 20;");

        layout.setRight(buttonBox);

        layout.setCenter(new javafx.scene.control.Label("Welcome Admin"));

        Scene homeScene = new Scene(layout, 600, 400);
        primaryStage.setScene(homeScene);
        primaryStage.setTitle("Home");
        primaryStage.show();
    }
}
