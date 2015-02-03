package test.connect.bdd;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.widget.ImageView;
import android.widget.TextView;
import com.iut.couchut.lebouche.presign.R;

/**
 * Created by Valentin on 03/02/2015.
 */
public class UserBoard extends Activity implements Callback {

    String maphoto="http://2.bp.blogspot.com/_EaAuSeshjRY/S2ucE6BFVQI/AAAAAAAAAI0/eHXM7SgSrUs/S150/salon+med-it.jpg";

    TextView username, authordesc;
    ImageView authorphoto;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.userboard);
        this.username=(TextView)findViewById(R.id.auteur_name);
        this.authordesc=(TextView)findViewById(R.id.auteur_text);
        this.authorphoto=(ImageView)findViewById(R.id.my_photo);
        this.authordesc.setText(text);
        handler= new Handler(this);
        ImageOperations(maphoto, handler);
        this.username.setText(Accueil.userlogin.username);
    }


    public boolean handleMessage(Message msg) {

        if(msg.obj!=null)
        {
            Bitmap bitmap= (Bitmap)msg.obj;
            this.authorphoto.setImageBitmap(bitmap);
        }
        return false;
    }


    private void ImageOperations(String url,Handler handler) {


        try {

            URL urls = new URL(url);
            Object content = urls.getContent();
            InputStream is = (InputStream)content;
            Bitmap bmt= BitmapFactory.decodeStream(is);
            this.authorphoto.setImageBitmap(bmt);
            Message message=handler.obtainMessage();
            message.arg1=1;
            message.obj=bmt;

        }
        catch(Exception e)
        {


        }
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
