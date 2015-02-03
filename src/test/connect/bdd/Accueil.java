package test.connect.bdd;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.iut.couchut.lebouche.presign.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Valentin on 03/02/2015.
 */
public class Accueil extends Activity {

    /**The url of to use if you work with emulator.*/
    public static final String URL_SERVER="http://10.0.2.2:8080/PreSignWebservice/";



    public static  final int SUCCES=1;
    public static final int ECHEC=-1;
    public static  final int CONNECTION_EXCEPTION=0;
    public User user=null;
    public static User userlogin;
    EditText mail;
    EditText password;
    static Context context;

    public ProgressDialog connectionloading;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context= this;

        setContentView(R.layout.main_activity);
        connectionloading= new ProgressDialog(this);
        connectionloading.setCancelable(true);


        final  UserLoaderHandler userLoaderHandler= new UserLoaderHandler(this);
        mail= (EditText)findViewById(R.id.etMail);
        password=(EditText)findViewById(R.id.etPassword);
        Button connect=(Button)findViewById(R.id.btConnexion);

        connect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                final  User userx= new User(mail.getText().toString(),password.getText().toString());
                connectionloading=ProgressDialog.show(v.getContext(), "Waiting Connection", "Connection");
                Accueil.testUserConnection(userx, userLoaderHandler);

            }
        });
    }

    public static User userConnect(User user) throws Exception
    {

        URL url = new URL(Accueil.URL_SERVER + "LoginUser?login=" +user.username+"&password="+user.userpassword);
        System.out.println(url.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() /100 != 2) {
            throw new IOException("Response not OK Version" + connection.getResponseCode());
        }

        InputStreamReader in= new InputStreamReader(connection.getInputStream());
        BufferedReader reader= new BufferedReader(in);
        StringBuffer sbf= new StringBuffer();
        String line= null;
        while((line=reader.readLine())!=null){
            sbf.append(line);
        }

        JSONObject jsonObject = new JSONObject(sbf.toString());

        if(jsonObject.getString("loginStatus").equals("yes")) return new User(jsonObject);
        return null;

    }

    public static void testUserConnection(final User user,final Handler receiver) {
        new Thread() {
            public void run() {

                try{

                    User users=	Accueil.userConnect(user);
                    Message msg = Message.obtain();
                    if(users!=null)
                    {
                        msg.arg1=Accueil.SUCCES;
                        msg.obj=users;
                    }
                    else
                    {
                        msg.arg1=Accueil.ECHEC;



                    }

                    receiver.sendMessage(msg);
                }catch (Exception e){
                    Message msg = Message.obtain();
                    msg.arg1 = CONNECTION_EXCEPTION;
                    msg.obj = e;
                    e.printStackTrace();
                    receiver.sendMessage(msg);
                }
            }
        }.start();
    }

    private class UserLoaderHandler extends Handler {

        private Context parent;


        public UserLoaderHandler(Context parent) {
            super();
            this.parent = parent;
        }
        public void handleMessage(Message msg) {

            connectionloading.dismiss();

            switch (msg.arg1) {
                case Accueil.SUCCES :
                    user = (User) msg.obj;
                    Accueil.userlogin=user;
                    Accueil.launchActivity(parent, UserBoard.class);
                    break;
                case Accueil.ECHEC :


                    break;
                case Accueil.CONNECTION_EXCEPTION :
                    Exception e = (Exception) msg.obj;
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                    builder.setTitle("Echec de connection ");
                    builder.setMessage("Netword connection problem");
                    builder.show();
                    break;
            }
        }
    }

    public static void launchActivity(Context context, Class<? extends Activity> activityClass) {
        Intent i = new Intent(context,activityClass);
        context.startActivity(i);
    }
    String text="Kalana est un chef lieu de la commune de Gouandiaka" +
            " située dans une zone aurifère.Il compte à peu près 1200" +
            " habitants et est situé au sud du Mali dans la région de Sikasso" +
            " cercle de Yanfolila, localité Wassoulou. Il contient 23 villages" +
            " dont Sadiouroula, Daoïla, Dadjiougoubala, solominina, Niessoussamala," +
            " kossiala, Hadjila, Zambala. Kalana est favorisé géographiquement" +
            " puisqu'il est seulement à 40km de la frontière guinéenne et subit " +
            "un climat favorable à l'agriculture ainsi qu'à l'élevage. Kalana est" +
            " une ville particulière du Mali puisqu'il a connu dans les années 1984," +
            " un essor spectaculaire dû à la présence d'or dans son solet revit encore" +
            " après la réouverture de l'usine de l'exploitation del'or SOMIKA dont la" +
            " population en profite difficilement.Kalana possède un marché hebdomadaire" +
            ", chaque lundi les habitants des villages environnants, " +
            "des commerçant de Yanfolila, Bougouni,Sikorolé, Sadiouroula " +
            "viennent célébrer ce jour de marché. Les habitants de Kalana" +
            " vivent de l'agriculture, de l'exploitation de l'or de l'élevage" +
            " et des métiers artisanaux. Parmi les fruits on peut compter " +
            "le mangue, les oranges etc... Doussou Mandé Sidibé est le premier " +
            "chef de canton qui collabore avec les colonisateurs";
}
