module org.example.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens org.example.frontend to javafx.fxml;
    exports org.example.frontend;
}