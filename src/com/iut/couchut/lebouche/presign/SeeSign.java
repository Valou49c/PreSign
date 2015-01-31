package com.iut.couchut.lebouche.presign;

import android.app.Activity;
import android.graphics.*;
import android.widget.*;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Valentin on 30/01/2015.
 */
public class SeeSign extends Activity {

    protected Model mdl = new Model();
    protected Client cli;
    protected dessinsignature msignature;
    private LinearLayout mcontent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_sign_activity);

        Bundle b = getIntent().getExtras();
        String identC = b.getString ("Identifiant");
        mdl.open();
        cli = mdl.trouveClient(identC);
        mdl.close();

        mcontent = (LinearLayout) findViewById(R.id.SeeSign);
        msignature = new dessinsignature(this);
        mcontent.addView(msignature, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        msignature.Dessine(cli.getSignature_Base64());

        findViewById(R.id.btReturn).setOnClickListener(envoyerListener);
    }

    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btReturn :
                    finish();
                    break;
            }
        }
    };

    public class dessinsignature extends View {
        private Canvas canvas;
        private Bitmap mBitmap;

        public dessinsignature(Context context) {
            super(context);
        }
        //gestion du dessin
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }

        public void Dessine( String encodedString) {
            try {
                byte[] encodeByte = Base64
                        .decode(encodedString, Base64.DEFAULT);
                mBitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                mBitmap = mBitmap.copy(mBitmap.getConfig(), true);

                Toast.makeText(SeeSign.this, "La signature est bonne mais la fonction dessine ne l'affiche pas =(", Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                Toast.makeText(SeeSign.this, "Probleme d'affichage de la signature", Toast.LENGTH_SHORT).show();
            }
            canvas = new Canvas(mBitmap);
            this.draw(canvas);
        }
    }

}
