module org.example.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens org.example.frontend to javafx.fxml;
    exports org.example.frontend.MainWindow;
    opens org.example.frontend.MainWindow to javafx.fxml;
    exports org.example.frontend.HomeWindow;
    opens org.example.frontend.HomeWindow to javafx.fxml;
    exports org.example.frontend.Records;
    opens org.example.frontend.Records to javafx.fxml;
    exports org.example.frontend.HomeWindow.UsersWindow;
    opens org.example.frontend.HomeWindow.UsersWindow to javafx.fxml;
    exports org.example.frontend.HomeWindow.MyBooksWindow;
    opens org.example.frontend.HomeWindow.MyBooksWindow to javafx.fxml;
    exports org.example.frontend.HomeWindow.AllBooksWindow;
    opens org.example.frontend.HomeWindow.AllBooksWindow to javafx.fxml;
}