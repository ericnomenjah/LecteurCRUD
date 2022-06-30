package com.example.tiabokyfx.Controller;


import com.example.tiabokyfx.Database.DatabaseConnection;
import com.example.tiabokyfx.Model.Lecteur;
import com.example.tiabokyfx.TiaBokyApplication;
import com.example.tiabokyfx.Views.EditLecteurForm;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    Stage stage;

    @FXML
    private Button LivresBtn;
    @FXML
    private Button PretsBtn;
    @FXML
    private Button lecteursBtn;

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private DatePicker naissanceField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField telephoneField;

    @FXML
    private TableView<Lecteur> lecteursTable;
    @FXML
    private TableColumn<Lecteur, Integer> numeroCol;
    @FXML
    private TableColumn<Lecteur, String> nomCol;
    @FXML
    private TableColumn<Lecteur, String> prenomCol;
    @FXML
    private TableColumn<Lecteur, String> naissanceCol;
    @FXML
    private TableColumn<Lecteur, String> adresseCol;
    @FXML
    private TableColumn<Lecteur, String> telephoneCol;
    @FXML TableColumn<Lecteur, String> actionsLecteursCol;

    ObservableList<Lecteur> listLECTEURS;

    int index = -1;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @FXML
    void OnLecteursBtnClicked(ActionEvent event) {
        initButton();
        lecteursBtn.setStyle("-fx-background-color : rgb(0, 176, 255);");
    }
    @FXML
    void OnLivresBtnClicked(ActionEvent event) {

        initButton();
        LivresBtn.setStyle("-fx-background-color : rgb(0, 176, 255);");
    }
    @FXML
    void OnPretsBtnClicked(ActionEvent event) {
        initButton();
        PretsBtn.setStyle("-fx-background-color : rgb(0, 176, 255);");
    }

    @FXML
    void btnlogoutClicked(ActionEvent event){
        Stage stage = (Stage) PretsBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void ajouterLecteurBtnClicked(ActionEvent event) {
        if(nomField.getText().isBlank() || prenomField.getText().isBlank() || adresseField.getText().isBlank() || telephoneField.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Veuiller remplir les champs correctement !!");
            alert.show();
        }else{
            Lecteur.addLecteur(nomField,prenomField,naissanceField,adresseField,telephoneField);
            refrechLecteursTable();
        }
    }

    private void refrechLecteursTable() {
        listLECTEURS = Lecteur.getLecteurs();
        lecteursTable.setItems(listLECTEURS);
    }
    @FXML
    void actualiserLecteurs(MouseEvent event) {
        refrechLecteursTable();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        refrechLecteursTable();

        numeroCol.setCellValueFactory(new PropertyValueFactory<Lecteur, Integer>("numero"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("prenom"));
        naissanceCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("naissance"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("adresse"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("telephone"));

        Callback<TableColumn<Lecteur, String>, TableCell<Lecteur,String>> cellFActory = (TableColumn<Lecteur, String> param) -> {
            //Make cell containing buttons
            final TableCell<Lecteur, String> cell = new TableCell<Lecteur, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    //Cell created only on non-empty rows
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {
                        Button btnUpdate = new Button("modifier");
                        Button btnDelete = new Button("supprimer");

                        btnUpdate.setStyle("-fx-cursor: hand;-fx-text-fill: #ffffff ;-fx-background-color: #00BFA6 ");
                        btnDelete.setStyle("-fx-cursor: hand; -fx-text-fill: #fff ; -fx-background-color:  #F50057 ");

                        btnDelete.setOnMouseClicked((MouseEvent event)->{
                            Lecteur lecteur = lecteursTable.getSelectionModel().getSelectedItem();
                            Lecteur.deleteLecteur(lecteur);
                            refrechLecteursTable();
                        });

                        btnUpdate.setOnMouseClicked((MouseEvent event)->{
                            Lecteur lecteur = lecteursTable.getSelectionModel().getSelectedItem();
                            //Afficher le MODAL
                            EditLecteurForm editLecteurForm = new EditLecteurForm();

                            FXMLLoader loader = new FXMLLoader (TiaBokyApplication.class.getResource("editLecteurForm.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                ex.getCause();
                            }

                            EditLecteurFormController editLecteurFormController = loader.getController();

                            editLecteurFormController.setTextField(lecteur.getNumero(),
                                    lecteur.getNom(),
                                    lecteur.getPrenom(),
                                    lecteur.getNaissance().toLocalDate(),
                                    lecteur.getAdresse(),
                                    lecteur.getTelephone());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.show();
                        });


                        HBox managebtn = new HBox(btnUpdate,btnDelete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnDelete, new Insets(2, 2, 0, 3));
                        HBox.setMargin(btnUpdate, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        actionsLecteursCol.setCellFactory(cellFActory);

    }


    public void initButton(){
        PretsBtn.setStyle("-fx-background-color : transparent;");
        lecteursBtn.setStyle("-fx-background-color : transparent;");
        LivresBtn.setStyle("-fx-background-color : transparent;");
    }
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

}
