package org.example.frontend.MainWindow;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    //Overriding start method with Application class to use window
    @Override
    public void start(Stage primaryStage) {
        //Creating the main layout
        BorderPane root = new BorderPane();

        //Creating vertical box layout
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        //Creating buttons
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        //Setting their size
        loginButton.setPrefSize(150, 50);
        registerButton.setPrefSize(150, 50);

        //Adding them to VBox
        buttonBox.getChildren().addAll(loginButton, registerButton);

        //Setting VBox with buttons to center
        root.setCenter(buttonBox);

        //Action when clicking the button
        loginButton.setOnAction(e -> {
            Login login = new Login(primaryStage);
            login.show();
        });

        registerButton.setOnAction(e -> {
            Register register = new Register(primaryStage);
            register.show();
        });

        //Creating new scene and setting the size
        Scene scene = new Scene(root, 400, 300);

        //Setting title of the window and displaying it
        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Main method to launch JavaFX
    public static void main(String[] args) {
        launch();
    }
}
