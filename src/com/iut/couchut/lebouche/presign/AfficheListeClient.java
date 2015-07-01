package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Valentin on 20/06/2015.
 */
public class AfficheListeClient extends Activity {

    private ListView lvListe;
    private List<Client> malistec;
    private Model mdl = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_liste_client);

        mdl.open();
        malistec = mdl.listClient();
        mdl.close();

        lvListe = (ListView)findViewById(R.id.lvListe);
        ClientAdapter adapter = new ClientAdapter(malistec, this);
        lvListe.setAdapter(adapter);

        lvListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent myIntent = new Intent(getApplicationContext(), ModifClient.class);
                myIntent.putExtra("Identifiant", malistec.get(position).getIdCli());
                startActivity(myIntent);
            }
        });
    }

}
