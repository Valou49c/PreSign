package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion extends Activity {

    public String addr = "http://www.fabrice-lebouche.fr/accueil.html";
    Model mdl = new Model();
    private AsyncTask<String, String, Boolean> mTrheadConnexion = null;
    private Button btConnexion;
    private String id;
    private String mdp;
    Client cli = new Client();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //addr = addr + "import.php";

        findViewById(R.id.btConnexion).setOnClickListener(envoyerListener);
        findViewById(R.id.btInscription).setOnClickListener(envoyerListener);

    }

    private View.OnClickListener envoyerListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mdl.open();
                    switch (v.getId()) {
                        case R.id.btInscription:
                            id = ((TextView)findViewById(R.id.etMail)).getText().toString();
                            mdp = ((TextView)findViewById(R.id.etPassword)).getText().toString();
//                            Intent myIntent = new Intent(getApplicationContext(), CaptureSign.class);
//                            myIntent.putExtra("Identifiant", cli.getIdCli());
////                            startActivity(myIntent);

                            if(id.length() > 0 && mdp.length() > 0){
                                if(mdl.listControleur().isEmpty()){
                                    String[] mesparams = { id, mdp, addr };
                                    mTrheadConnexion = new ConnexionServer(Connexion.this).execute(mesparams);
                                }
                                else {
                                    String txt = "";
                                    if(mdl.listControleur().get(0).getIdentifiant() != id && mdl.listControleur().get(0).getMotPasse() == mdp){
                                        txt = "Modification de l'identifiant";
                                    }
                                    else{
                                        txt = "Modification du mot du passe";
                                    }

                                    Toast.makeText(Connexion.this, txt, Toast.LENGTH_LONG).show();
                                }
                            }
                            break;
                        case R.id.btConnexion:
                            Intent myIntent1 = new Intent(getApplicationContext(), SignIt.class);
                            myIntent1.putExtra("Identifiant", cli.getIdCli());
                            startActivity(myIntent1);
                            break;
                    }
                    mdl.close();
            //Toast.makeText(MainActivity.this, texte, Toast.LENGTH_SHORT).show();
        }
    };

    public void alertmsg(String title, String msg) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(title);
        adb.setPositiveButton("Ok", null);
        adb.setMessage(msg);
        adb.show();
    }

    public void reponseserveur(StringBuilder sb)
    {
        if(Integer.parseInt(sb.toString()) > 0) {
            mdl.open();
            mdl.saveControleur(new Controleur(id, mdp));
            mdl.close();

            setResult(1);
            finish();
        }
        else Toast.makeText(Connexion.this, "Mauvaise combinaison Login / Mdp" , Toast.LENGTH_LONG).show();
    }

	/*public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}*/



}
