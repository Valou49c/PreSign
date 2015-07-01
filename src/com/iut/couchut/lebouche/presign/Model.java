package com.iut.couchut.lebouche.presign;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.provider.BaseColumns;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentin on 30/01/2015.
 */
public class Model {


    private String DB4OFILENAME;
    private ObjectContainer db;
    private File appDir;

    public void open(){
        DB4OFILENAME=Environment.getExternalStorageDirectory()+"/baseDB4O"+"/BasePreSign.db4o";
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
    }

    public void mkdir(){
        appDir = new File(Environment.getExternalStorageDirectory()+"/baseDB4O");
        if(!appDir.exists() && !appDir.isDirectory())
        {
            appDir.mkdirs();
        }
    }

    public ArrayList<Controleur> listControleur(){
        ObjectSet<Controleur> result = db.queryByExample(Controleur.class);
        ArrayList<Controleur> lesControleurs = new ArrayList<Controleur>();

        while (result.hasNext()) {
            lesControleurs.add((Controleur)result.next());
        }
        return lesControleurs;
    }

    public ArrayList<Client> listClient(){
        ObjectSet<Client> result = db.queryByExample(Client.class);
        ArrayList<Client> lesClients = new ArrayList<Client>();

        while (result.hasNext()) {
            lesClients.add((Client)result.next());
        }
        return lesClients;
    }




    public Controleur trouveControleur(String id){
        Controleur vretour = new Controleur();
        vretour.setIdentifiant(id);
        ObjectSet<Controleur> result = db.queryByExample(vretour);

        while (result.hasNext()) {
            vretour = (Controleur)result.next();
        }
        return vretour;
    }

    public Client trouveClient(String id){
        Client vretour = new Client();
        vretour.setIdCli(id);
        ObjectSet<Client> result = db.queryByExample(vretour);

        while (result.hasNext()) {
            vretour = (Client)result.next();
        }
        return vretour;
    }

    public void saveControleur(Controleur unControleur){
        Controleur vretour = trouveControleur(unControleur.getIdentifiant());
        if (vretour.getIdentifiant() != null){
            vretour.recopieControleur(unControleur);
            db.store(vretour);
        }
        else {
            db.store(unControleur);
        }
    }

    public void saveClient(Client unClient){
        Client vretour = trouveClient(unClient.getIdCli());
        if (vretour.getIdCli() != null){
            vretour.recopieClient(unClient);
            db.store(vretour);
        }
        else {
            db.store(unClient);
        }
    }



    @SuppressLint("SimpleDateFormat")
    public void chargeDb() {
        ArrayList<Client> lesClients = listClient();
        Client vcli = new Client();

        Boolean test = true;
        if (lesClients.isEmpty()) {
            try {
                vcli = new Client("1", "Couchut", "Valentin", "Le clos ste Anne", "Brissac-Quince", "49320", "0609466095",
                        "Valou49c@gmail.com", "password","");
            }
            catch (Exception e) {

               // e.printStackTrace();
            }
            db.store(vcli);

            try {
                vcli = new Client("2", "Lebouche", "Fabrice", "Parc des princes", "Paris", "75003", "060659863",
                        "fabrice.lebouche.sio@gmail.com", "password","");
            }
            catch (Exception e) {

                e.printStackTrace();
            }
            db.store(vcli);

            try {
                vcli = new Client("3", "Mamadou", "Sakho", "test", "Paris", "75003", "060659863",
                        "fabrice.lebouche.sio@gmail.com", "password","");
            }
            catch (Exception e) {

                e.printStackTrace();
            }
            db.store(vcli);

        }
    }



    public void close(){
        db.close();
    }

    public Model(){
        mkdir();
        open();
        db.close();
    }

    public void delclient(){
        ArrayList<Client> lesClients = this.listClient();

        for (Client c : lesClients) {
            db.delete(c);
        }
    }

    public void addclient(List<Client> vlist){
        for (Client c : vlist) {
            db.store(c);
        }
    }





}




