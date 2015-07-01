package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Valentin on 30/06/2015.
 */
public class AfficheListeEleveREST extends Activity {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://169.254.178.130/customer";


    // JSON Node names

    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "lastname";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";
    private String id;
    private String mdp;



    // contacts JSONArray
    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

        private ListView lvListe;
        private List<Client> malistec;
        private Model mdl = new Model();

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            contactList = new ArrayList<HashMap<String, String>>();


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_liste_eleve);

//            mdl.open();
//            malistec = mdl.listClient();
             new GetContacts().execute();
//            mdl.close();

            lvListe = (ListView)findViewById(R.id.lvListe);


           lvListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                   Intent myIntent = new Intent(getApplicationContext(), ModifClient.class);
                   myIntent.putExtra("Identifiant", contactList.get(position).toString());
                   startActivity(myIntent);
               }
           });
        }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AfficheListeEleveREST.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String email = c.getString(TAG_EMAIL);
//                        String address = c.getString(TAG_ADDRESS);
//                        String gender = c.getString(TAG_GENDER);

//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject(TAG_PHONE);
//                        String mobile = phone.getString(TAG_PHONE_MOBILE);
//                        String home = phone.getString(TAG_PHONE_HOME);
//                        String office = phone.getString(TAG_PHONE_OFFICE);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_ID, id);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_EMAIL, email);
//                        contact.put(TAG_PHONE_MOBILE, mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    AfficheListeEleveREST.this, contactList,
//                    R.layout.activity_eleve_au_detail, new String[] { TAG_NAME, TAG_EMAIL,TAG_ID },
//                    new int[] { R.id.name, R.id.email, R.id.mobile});
//
//            setListAdapter(adapter);
        }

    }
}

