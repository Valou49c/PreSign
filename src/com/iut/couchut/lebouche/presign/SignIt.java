package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Valentin on 02/02/2015.
 */
public class SignIt extends Activity {

    private PreSignDataSource datasource;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signit_activity);

        datasource = new PreSignDataSource(this);
        try {
            datasource.open();
            Toast.makeText(getApplicationContext(), "Bdd Open", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Sera appelée par l'attribut onClick
    // des boutons déclarés dans main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")

        Client client = null;
        switch (view.getId()) {
            case R.id.add:
//                String[] users = new String[] { "couchut", "valentin", "Le clos ste anne", "49320", "Brissac",
//                        "0609466095", "Valou49c@gmail.com", "test", "" };
                int nextInt = new Random().nextInt(3);
                // enregistrer le nouveau commentaire dans la base de données
                client = datasource.createUser("couchut", "valentin", "Le clos ste anne", "49320", "Brissac",
                          "0609466095", "Valou49c@gmail.com", "test", "ghfy");
            Toast.makeText(getApplicationContext(), "Ajout user OK", Toast.LENGTH_LONG).show();
                break;
        }
    }
}