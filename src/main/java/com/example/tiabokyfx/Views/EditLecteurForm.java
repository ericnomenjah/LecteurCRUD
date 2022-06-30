package com.example.tiabokyfx.Views;

import com.example.tiabokyfx.Controller.DashboardController;
import com.example.tiabokyfx.Controller.EditLecteurFormController;
import com.example.tiabokyfx.Model.Lecteur;
import com.example.tiabokyfx.TiaBokyApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditLecteurForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TiaBokyApplication.class.getResource("editLecteurForm.fxml"));
        Parent root = loader.load();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
