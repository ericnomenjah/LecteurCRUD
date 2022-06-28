module com.example.tiabokyfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tiabokyfx to javafx.fxml;
    exports com.example.tiabokyfx;
    exports com.example.tiabokyfx.Controller;

    opens com.example.tiabokyfx.Controller to javafx.fxml;
}