module com.mazmorra {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mazmorra to javafx.fxml;
    exports com.mazmorra;
}
