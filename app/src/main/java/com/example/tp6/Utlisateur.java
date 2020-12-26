package com.example.tp6;

public class Utlisateur {
    int id;
    String login;
    String mp;
    String nom;
    String prenom;
    public Utlisateur (){   }

    public Utlisateur(int id,String login,String mp,String nom,String prenom){
        this.id=id;
        this.login=login;
        this.mp=mp;
        this.nom=nom;
        this.prenom=prenom;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getMp() {
        return mp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}
