package com.example.tiabokyfx.Controller;

import com.example.tiabokyfx.Model.Lecteur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditLecteurFormController {

    @FXML
    private TextField adresseField;

    @FXML
    private Button ajouterLecteurBtn;

    @FXML
    private Button ajouterLecteurBtn1;

    @FXML
    private DatePicker naissanceField;

    @FXML
    private TextField nomField;

    @FXML
    private Label numeroLabel;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField telephoneField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ajouterLecteurBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void modifierLecteurBtn(ActionEvent event) {
        if(nomField.getText().isBlank() || prenomField.getText().isBlank() || adresseField.getText().isBlank() || telephoneField.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Merci de remplir le formulaire correctement");
            alert.show();
        }else{
            Lecteur.updateLecteur(numeroLabel.getText(),nomField.getText(),prenomField.getText(),naissanceField.getValue(),adresseField.getText(),telephoneField.getText());
        }
    }

    public void setTextField(int numero, String nom, String prenom, LocalDate toLocalDate, String adresse, String telephone) {
        numeroLabel.setText((String.valueOf(numero)));
        nomField.setText(nom);
        prenomField.setText(prenom);
        naissanceField.setValue(toLocalDate);
        adresseField.setText(adresse);
        telephoneField.setText(telephone);
    }
}