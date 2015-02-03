package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Valentin on 02/02/2015.
 */
public class ConnexionServer extends AsyncTask<String, String, Boolean> {

        // Référence à l'activité qui appelle
        private WeakReference<Activity> mActivity = null;
        private String vclassactivity;
        private StringBuilder sb = new StringBuilder();

        public ConnexionServer (Activity pActivity) {
        mActivity = new WeakReference<Activity>(pActivity);
        //permet de récupérer la class de l'appelant
        vclassactivity=pActivity.getClass().toString();
    }

        @Override
        protected void onPreExecute () {// Au lancement, on envoie un message à l'appelant
        //if(mActivity.get() != null) Toast.makeText(mActivity.get(), "Thread on démarre",Toast.LENGTH_SHORT).show();
    }

        @Override
        protected void onPostExecute (Boolean result) {
        if (mActivity.get() != null) {
            if(result){
                //pour exemple on appelle une méthode de l'appelant qui va gérer la fin ok du thread
                if (vclassactivity.contains("Identification"))
                {
                    ((Connexion)mActivity.get()).reponseserveur(sb);
                }

                if (vclassactivity.contains("ImportDonne"))
                {
//                    ((ImportDonne)mActivity.get()).retourimport(sb);
                }
            }
        }
    }
        @Override
        protected Boolean doInBackground (String... params) {// Exécution en arrière plan
        String vid = "", vpass = "", vurl = "";
        if (vclassactivity.contains("Identification")) {
            vid = params[0];
            vpass = params[1];
            vurl = params[2];
        }
        if (vclassactivity.contains("ImportDonne")) {
            vid = params[0];
            vurl = params[1];
        }

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(vurl); //passée par paramètre
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            // selon l'activity appelante on peut passer des paramètres en JSON exemple
            if (vclassactivity.contains("Identification"))
            {
                // Création objet jsonn clé valeur
                JSONObject jsonParam = new JSONObject();
                // Exemple Clé valeur utiles à notre application
                jsonParam.put("ID", vid);
                jsonParam.put("pass", vpass);
                out.write(jsonParam.toString());
                out.flush();
            }
            if (vclassactivity.contains("ImportDonne"))
            {
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("ID", vid);
                out.write(jsonParam.toString());
                out.flush();
            }
            out.close();
            // récupération du serveur
            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                String[] vstring0 = { "Reçu du serveur",sb.toString()};
                //publishProgress(vstring0);
                // on se servira du sb dans la méthode onPostExecute pour appel de la gestion de 	fin de thread si ok
            }
            else {
                String[] vstring0 = { "Erreur",urlConnection.getResponseMessage()};
                publishProgress(vstring0);
            }
            // gestion des erreurs
        } catch (MalformedURLException e) {
            String[] vstring0 = { "Erreur", "Pbs url" };
            publishProgress(vstring0);
            return false;
        } catch (java.net.SocketTimeoutException e) {
            String[] vstring0 = { "Erreur", "temps trop long" };
            publishProgress(vstring0);
            return false;
        } catch (IOException e) {
            String[] vstring0 = { "Erreur", "Pbs IO" };
            publishProgress(vstring0);
            return false;
        } catch (JSONException e) {
            String[] vstring0 = { "Erreur", "Pbs json" };
            publishProgress(vstring0);
            return false;
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return true;
    }

        @Override
        protected void onProgressUpdate (String... param) {
        // utilisation de on progress pour afficher des message pendant le doInBackground
        // ici pour exemple on appelle une méthode de l'appelant qui peut par exemple modifier son layout
        if (vclassactivity.contains("Identification"))
        {
            ((Connexion)mActivity.get()).alertmsg(param[0],param[1]);
        }
    }
        @Override
        protected void onCancelled () {
        if(mActivity.get() != null) Toast.makeText(mActivity.get(), "Annulation", Toast.LENGTH_SHORT).show();
    }
}
