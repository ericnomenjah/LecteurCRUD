package com.example.tiabokyfx.Model;

import com.example.tiabokyfx.Database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;

public class Lecteur {

    int numero;
    String nom;
    String prenom;
    Date naissance;
    String adresse;
    String telephone;

    public static void addLecteur(TextField nomField, TextField prenomField, DatePicker naissanceField, TextField adresseField, TextField telephoneField) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "INSERT INTO lecteur (nom,prenom,naissance,adresse,telephone) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,nomField.getText());
            preparedStatement.setString(2,prenomField.getText());
            preparedStatement.setString(3,String.valueOf(naissanceField.getValue()));
            preparedStatement.setString(4,adresseField.getText());
            preparedStatement.setString(5, telephoneField.getText());
            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Nouveau lecteur enregistrer :) ");
            alert.show();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public static void updateLecteur(String numero, String nom, String prenom, LocalDate naissance, String adresse, String telephone){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "UPDATE lecteur SET nom=?,prenom=?,naissance=?,adresse=?,telephone=? WHERE numero =" + numero;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setString(3,String.valueOf(naissance));
            preparedStatement.setString(4,adresse);
            preparedStatement.setString(5,telephone);
            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Lecteur Mis à jour");
            alert.show();
        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public static void deleteLecteur(Lecteur lecteur){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "DELETE FROM `lecteur` WHERE numero = " + lecteur.getNumero();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Lecteur Supprimer ");
            alert.show();
        }catch (SQLException exception){
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Lecteur(int numero, String nom, String prenom, Date naissance, String adresse, String telephone) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public static ObservableList<Lecteur> getLecteurs(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        ObservableList<Lecteur> listLecteur = FXCollections.observableArrayList();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lecteur");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                listLecteur.add(new Lecteur(
                        Integer.parseInt(resultSet.getString("numero")),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("naissance"),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone")
                        ));
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }

        return listLecteur;
    }
}
