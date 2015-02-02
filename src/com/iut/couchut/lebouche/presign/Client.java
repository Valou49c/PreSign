package com.iut.couchut.lebouche.presign;

/**
 * Created by Valentin on 30/01/2015.
 */
public class Client {

    // Properties
    private String idCli;
    private String nomCli;
    private String adresse, ville, cp, tel;
    private String prenomCli;
    private String emailCli;
    private String passwordCli;
    private String signature_Base64;

   // Constructor

    public Client() {
    }

    public void recopieClient(Client unClient) {
        this.idCli = unClient.idCli;
        this.nomCli = unClient.nomCli;
        this.prenomCli = unClient.prenomCli;
        this.adresse = unClient.adresse;
        this.cp = unClient.cp;
        this.ville = unClient.ville;
        this.tel = unClient.tel;
        this.emailCli = unClient.emailCli;
        this.passwordCli = unClient.passwordCli;
        this.signature_Base64 = unClient.signature_Base64;
    }

    public Client(String idCli, String nomCli, String prenomCli, String adresse,
                   String cp, String ville, String tel,  String emailCli, String passwordCli, String signature_Base64) {
        this.idCli = idCli;
        this.nomCli = nomCli;
        this.prenomCli = prenomCli;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.tel = tel;
        this.emailCli = emailCli;
        this.passwordCli = passwordCli;
        this.signature_Base64 = signature_Base64;
    }


    public Client(String idCli, String nomCli, String prenomCli, String tel,  String emailCli,
                  String passwordCli, String signature_Base64) {
        this.idCli = idCli;
        this.nomCli = nomCli;
        this.prenomCli = prenomCli;
//        this.adresse = adresse;
//        this.cp = cp;
//        this.ville = ville;
        this.tel = tel;
        this.emailCli = emailCli;
        this.passwordCli = passwordCli;
        this.signature_Base64 = signature_Base64;
    }

    // Getter and Setter

    public String getIdCli() {
        return idCli;
    }

    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPrenomCli() {
        return prenomCli;
    }

    public void setPrenomCli(String prenomCli) {
        this.prenomCli = prenomCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getPasswordCli() {
        return passwordCli;
    }

    public void setPasswordCli(String passwordCli) {
        this.passwordCli = passwordCli;
    }

    public String getSignature_Base64() {
        return signature_Base64;
    }

    public void setSignature_Base64(String signature_Base64) {
        this.signature_Base64 = signature_Base64;
    }

}
