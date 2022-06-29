module com.example.tiabokyfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;


    opens com.example.tiabokyfx to javafx.fxml;
    exports com.example.tiabokyfx;
    exports com.example.tiabokyfx.Controller;

    opens com.example.tiabokyfx.Model to javafx.base;
    opens com.example.tiabokyfx.Controller to javafx.fxml;
}