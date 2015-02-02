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
    Client user = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationdrawer);

        datasource = new PreSignDataSource(this);
        try {
            datasource.open();
            Toast.makeText(getApplicationContext(), "Bdd Open", Toast.LENGTH_LONG).show();
            int nextInt = new Random().nextInt(3);
                    // save the new user to the database
                    user = datasource.createUser(String.valueOf(nextInt),"couchut", "valentin", "Le clos ste anne", "49320", "Brissac",
                            "0609466095", "Valou49c@gmail.com", "test");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        findViewById(R.id.btInscriptionUser).setOnClickListener(envoyerListener);
    }


    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Client user = null;
            switch (v.getId()) {
                case R.id.btInscriptionUser:

                    Toast.makeText(getApplicationContext(), "Clik sur bt annuler", Toast.LENGTH_SHORT).show();
//                    int nextInt = new Random().nextInt(3);
//                    // save the new user to the database
//                    user = datasource.createUser("couchut", "valentin", "Le clos ste anne", "49320", "Brissac",
//                            "0609466095", "Valou49c@gmail.com", "test", "");
//                    Toast.makeText(getApplicationContext(), "Ajout user OK", Toast.LENGTH_LONG).show();
//                    break;
            }

        }
    };
}