package com.example.tiabokyfx.Model;

import com.example.tiabokyfx.Database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Lecteur {

    int numero;
    String nom;
    String prenom;
    Date naissance;
    String adresse;
    String telephone;

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
