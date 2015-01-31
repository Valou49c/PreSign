package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Connexion extends Activity {

    public String addr = "http://notreserver.com/";
    Model mdl = new Model();
    //    private AsyncTask<String, String, Boolean> mTrheadConnexion = null;
    private Button btConnexion;
    private String id;
    private String mdp;
    Client cli = new Client();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.btConnexion).setOnClickListener(envoyerListener);
        findViewById(R.id.btTest).setOnClickListener(envoyerListener);


    }

    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btConnexion:
                    Intent myIntent = new Intent(getApplicationContext(), CaptureSign.class);
                    myIntent.putExtra("Identifiant", cli.getIdCli());
                    startActivity(myIntent);
                    break;
                case R.id.btTest:
                    if (cli.getSignature_Base64().length() > 0) {
                        Intent myIntent1 = new Intent(getApplicationContext(), SeeSign.class);
                        myIntent1.putExtra("Identifiant", cli.getIdCli());
                        startActivity(myIntent1);
                    }
                    break;

            }
            //Toast.makeText(MainActivity.this, texte, Toast.LENGTH_SHORT).show();
        }
    };
}
