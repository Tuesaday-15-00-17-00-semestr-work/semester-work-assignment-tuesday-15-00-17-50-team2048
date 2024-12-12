package org.example.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setPrefSize(150, 50);
        registerButton.setPrefSize(150, 50);

        buttonBox.getChildren().addAll(loginButton, registerButton);

        root.setCenter(buttonBox);

        loginButton.setOnAction(e -> {
            Login login = new Login(primaryStage);
            login.show();
        });

        registerButton.setOnAction(e -> {
            Register register = new Register(primaryStage);
            register.show();
        });

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
