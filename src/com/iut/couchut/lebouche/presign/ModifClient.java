package com.iut.couchut.lebouche.presign;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Valentin on 20/06/2015.
 */
public class ModifClient extends Activity{

    private Model mdl = new Model();
    private Client cli;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_client);

        Bundle b = getIntent().getExtras();
        String identC = b.getString ("Identifiant");

        mdl.open();
        cli = mdl.trouveClient(identC);
        mdl.close();

        ((TextView)findViewById(R.id.id)).setText(cli.getIdCli());
        ((TextView)findViewById(R.id.iden)).setText(cli.getNomCli() + ' ' + cli.getPrenomCli());
        ((TextView)findViewById(R.id.tel)).setText(cli.getTel());
        ((TextView)findViewById(R.id.addr)).setText(cli.getAdresse() + ' ' + cli.getCp() + ' ' + cli.getVille());

        findViewById(R.id.Button01).setOnClickListener(envoyerListener);
        findViewById(R.id.Button02).setOnClickListener(envoyerListener);
//        findViewById(R.id.bGeo).setOnClickListener(envoyerListener);
        findViewById(R.id.bSign).setOnClickListener(envoyerListener);
        findViewById(R.id.bVueSign).setOnClickListener(envoyerListener);
    }

    private void save(){
//        int day = ((DatePicker)findViewById(R.id.calendar)).getDayOfMonth();
//        int month = ((DatePicker)findViewById(R.id.calendar)).getMonth();
//        int year = ((DatePicker)findViewById(R.id.calendar)).getYear();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day);

//        cli.set(Double.parseDouble(((TextView)findViewById(R.id.rel)).getText().toString()));
//        cli.setDate_dernier_releve(calendar.getTime());
//        cli.setSituation(Integer.parseInt(((TextView)findViewById(R.id.situ)).getText().toString()));

        mdl.open();
        mdl.saveClient(cli);
        mdl.close();
    }

    private boolean verif(){
        boolean v = false;

//        int situ = Integer.parseInt(((TextView)findViewById(R.id.situ)).getText().toString());
//        if (situ > 0 && situ < 7){
//            double relc = Double.parseDouble(((TextView)findViewById(R.id.rel)).getText().toString());
//            double relini = Double.parseDouble(((TextView)findViewById(R.id.anc_rel)).getText().toString());
//            if(relc > relini && (situ == 1 || situ == 5 || situ == 6)){
//
//                int day = ((DatePicker)findViewById(R.id.calendar)).getDayOfMonth();
//                int month = ((DatePicker)findViewById(R.id.calendar)).getMonth();
//                int year = ((DatePicker)findViewById(R.id.calendar)).getYear();
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(year, month, day);

//                if(calendar.getTime().after(cli.getDate_ancien_releve())){
//                    v = true;
//                }
               // else Toast.makeText(ModifClient.this, "Date du dernier relevé inférieure", Toast.LENGTH_SHORT).show();
//            }
//           // else Toast.makeText(ModifClient.this, "Nouveau relevé inférieure", Toast.LENGTH_SHORT).show();
//        }
       // else Toast.makeText(ModifClient.this, "Mauvaise situation", Toast.LENGTH_SHORT).show();

        return v;
    }

    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Button01 :
//                    if(verif()){
                        save();
                        finish();
//                    }
                    break;
                case R.id.Button02 :
                    finish();
                    break;
                case R.id.bSign :
//                    if (verif()){
                        save();
                        Intent myIntent = new Intent(getApplicationContext(), CaptureSign.class);
                        myIntent.putExtra("Identifiant", cli.getIdCli());
                        startActivity(myIntent);
//                    }
                    break;
                case R.id.bVueSign :
//                    if(cli.getSignature_Base64().length() > 0){
                        Intent myIntent1 = new Intent(getApplicationContext(), SeeSign.class);
                        myIntent1.putExtra("Identifiant", cli.getIdCli());
                        startActivity(myIntent1);
//                    }
                    break;
            }
        }
    };
}
