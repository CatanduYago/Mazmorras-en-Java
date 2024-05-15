module com.mvcobserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.mvcobserver to javafx.fxml;
    exports com.mvcobserver;
}
