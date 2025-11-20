module com.example.javapractic4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens com.example.javapractic4 to javafx.fxml;
    exports com.example.javapractic4;
    exports tables;
    opens tables to javafx.fxml;
}