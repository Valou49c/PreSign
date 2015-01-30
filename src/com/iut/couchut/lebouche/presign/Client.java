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
    private String signatureBase64;

   // Constructor

    public Client() {
    }

    public void recopieClient(Client unClient) {
        this.idCli = idCli;
        this.nomCli = nomCli;
        this.prenomCli = prenomCli;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.tel = tel;
        this.emailCli = emailCli;
        this.passwordCli = passwordCli;
        this.signatureBase64 = signatureBase64;
    }

    public Client(String idCli, String nomCli, String prenomCli, String adresse,
                  String ville, String cp, String tel,  String emailCli, String passwordCli) {
        this.idCli = idCli;
        this.nomCli = nomCli;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.tel = tel;
        this.prenomCli = prenomCli;
        this.emailCli = emailCli;
        this.passwordCli = passwordCli;
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

    public String getSignatureBase64() {
        return signatureBase64;
    }

    public void setSignatureBase64(String signatureBase64) {
        this.signatureBase64 = signatureBase64;
    }

}
