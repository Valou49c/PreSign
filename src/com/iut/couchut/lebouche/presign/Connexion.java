package com.iut.couchut.lebouche.presign;

import EDU.purdue.cs.bloat.decorate.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Connexion extends Activity {

    public String addr = "http://notreserver.com/";
    Model mdl = new Model();
//    private AsyncTask<String, String, Boolean> mTrheadConnexion = null;
    private Button btConnexion;
    private String id;
    private String mdp;
    Client cli = new Client();
    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btConnexion :
                    Intent myIntent = new Intent(getApplicationContext(), CaptureSign.class);
                    myIntent.putExtra("Identifiant", cli.getIdCli());
                    startActivity(myIntent);
                    break;

            }
            //Toast.makeText(MainActivity.this, texte, Toast.LENGTH_SHORT).show();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.btConnexion).setOnClickListener(envoyerListener);

    }
}
