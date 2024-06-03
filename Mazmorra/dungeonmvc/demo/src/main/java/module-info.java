module com.dugeonmvc {
   requires javafx.controls;
   requires javafx.fxml;
   requires javafx.graphics;

   exports com.dungeonmvc;
   exports com.dungeonmvc.controllers;

   opens com.dungeonmvc to javafx.fxml;
   opens com.dungeonmvc.controllers to javafx.fxml;
}
