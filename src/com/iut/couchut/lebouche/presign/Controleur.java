package com.iut.couchut.lebouche.presign;

/**
 * Created by Valentin on 30/01/2015.
 */
public class Controleur {

    private String id, mp;

    public String getIdentifiant() {
        return id;
    }

    public void setIdentifiant(String id) {
        this.id = id;
    }

    public String getMotPasse() {
        return mp;
    }

    public void setMotPasse(String mp) {
        this.mp = mp;
    }

    public Controleur (String id, String Mp){
        this.id = id;
        this.mp = Mp;
    }

    public Controleur (){}

    public void recopieControleur (Controleur unControleur){
        this.id = unControleur.getIdentifiant();
        this.mp = unControleur.getMotPasse();
    }











}





