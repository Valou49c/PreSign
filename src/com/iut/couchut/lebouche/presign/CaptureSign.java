package com.iut.couchut.lebouche.presign;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

/**
 * Created by Valentin on 30/01/2015.
 */
public class CaptureSign extends Activity{

    private Model mdl = new Model();
    private Client cli;
    private LinearLayout mcontent ;
    protected dessinsignature msignature;
    protected Button bSave;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_sign_activity);

        Bundle b = getIntent().getExtras();
        String identC = b.getString("Identifiant");
        mdl.open();
        cli = mdl.trouveClient(identC);
        mdl.close();

        String lig1 = "Personne : " + cli.getIdCli() + " " + cli.getNomCli() + " " + cli.getPrenomCli();
        ((TextView)findViewById(R.id.tvInfoCli)).setText(lig1);

        bSave = (Button) findViewById(R.id.btSave);
        bSave.setEnabled(false);
        findViewById(R.id.btSave).setOnClickListener(envoyerListener);
        findViewById(R.id.btCancel).setOnClickListener(envoyerListener);
        findViewById(R.id.btClear).setOnClickListener(envoyerListener);

        mcontent = (LinearLayout) findViewById(R.id.Capture);

        msignature = new dessinsignature(this, null, lig1,null);

        mcontent.addView(msignature, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
    }



    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btSave :
                    cli.setSignatureBase64(msignature.save());
                    mdl.open();
                    mdl.saveClient(cli);
                    mdl.close();
                    finish();
                    break;
                case R.id.btCancel :
                    finish();
                    break;
                case R.id.btClear :
                    msignature.reset();
                    bSave.setEnabled(false);
                    break;
            }
        }
    };

    public class dessinsignature extends View {
        // variables nécessaire au dessin
        private Canvas canvas;
        private Paint paint = new Paint();
        private Path path = new Path();// collection de l'ensemble des points sauvegardés lors des mouvements du doigt
        private Bitmap mBitmap;
        private String lig1;
        private String lig2;
        public dessinsignature(Context context, AttributeSet attrs, String lig1, String lig2) {
            super(context, attrs);
            this.lig1 = lig1;
            this.lig2 = lig2;
            this.setBackgroundColor(Color.WHITE);
            paint.setAntiAlias(true); // empêche le scintillement gourmand en cpu et mémoire
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5f); //taille de la grosseur du trait en pixel
            paint.setTextSize(20);// taille du texte pur afficher les lignes
        }
        //gestion du dessin
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(lig1, 20, 30, paint);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
        }
        // gestion des événements du doigt
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            if(!bSave.isEnabled()) bSave.setEnabled(true);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(eventX, eventY);
                    break;
                case MotionEvent.ACTION_UP:
                    // nothing to do
                    break;
                default:
                    return false;
            }
            invalidate();
            return true;
        }

        public void reset(){
            this.path.reset();
            invalidate();
        }

        public String save() {
            String vretour;
            if (mBitmap == null) {
                mBitmap = Bitmap.createBitmap(this.getWidth(),
                        this.getHeight(), Bitmap.Config.RGB_565);
            }
            try {
                canvas = new Canvas(mBitmap);
                this.draw(canvas);
                ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteStream);
                byte[] b = ByteStream.toByteArray();
                vretour = Base64.encodeToString(b, Base64.DEFAULT);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Problème lors de l'enregistrement", Toast.LENGTH_SHORT).show();
                vretour = null;
            }
            return vretour;
        }

        public void Dessine( String encodedString) {
            try {
                byte[] encodeByte = Base64
                        .decode(encodedString, Base64.DEFAULT);
                mBitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                        encodeByte.length);
                mBitmap = mBitmap.copy(mBitmap.getConfig(), true);
            } catch (Exception e) {
                // erreur dessine
            }
            canvas = new Canvas(mBitmap);
            this.draw(canvas);
        }
    }




}
