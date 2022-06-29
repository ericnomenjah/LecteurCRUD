package com.example.tiabokyfx.Views;

import com.example.tiabokyfx.Controller.DashboardController;
import com.example.tiabokyfx.TiaBokyApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dashboard extends Application {
    Stage stage;

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TiaBokyApplication.class.getResource("dashboard.fxml"));
        Parent root = loader.load();

        DashboardController dashboardController = loader.getController();

        dashboardController.setStage(primaryStage);
        this.stage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
