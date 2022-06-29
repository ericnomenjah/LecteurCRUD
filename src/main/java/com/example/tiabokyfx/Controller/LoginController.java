package com.example.tiabokyfx.Controller;

import com.example.tiabokyfx.Database.DatabaseConnection;
import com.example.tiabokyfx.Views.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Label MessageLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void closeApplication(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginButtonAction(ActionEvent event) {
        if(usernameField.getText().isBlank() || passwordField.getText().isBlank()){
            MessageLabel.setText("Username and Password is required !!");
            MessageLabel.setVisible(true);
        }else{
            validateLogin();
        }
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLoginQuery = "SELECT COUNT(1) FROM utilisateur WHERE name='" + usernameField.getText() + "' AND password='" + passwordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginQuery);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();

                    //Afficher le homepage
                    Dashboard dashboard = new Dashboard();
                    dashboard.start(new Stage());
                }else{
                    MessageLabel.setText("Invalid Login, please try again!!");
                    MessageLabel.setVisible(true);
                }
            }

        } catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }

}
