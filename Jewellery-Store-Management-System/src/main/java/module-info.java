module com.JewelleryStoreManagementSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    requires java.desktop;

    opens com.example.JewelleryStoreManagementSystem to javafx.fxml;
    exports com.example.JewelleryStoreManagementSystem;

    opens com.example.JewelleryStoreManagementSystem.Controllers to javafx.fxml;
    exports com.example.JewelleryStoreManagementSystem.Controllers;



}