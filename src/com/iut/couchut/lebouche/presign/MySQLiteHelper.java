package com.iut.couchut.lebouche.presign;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Valentin on 02/02/2015.
 */
public final class MySQLiteHelper extends SQLiteOpenHelper {



    public static final String TABLE_USERS = "users";
    public static final String COLUMN_NAME_USER_ID = "userid";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_LASTNAME = "lastname";
    public static final String COLUMN_NAME_ADRESS = "adress";
    public static final String COLUMN_NAME_CP = "CP";
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_TEL = "mobile";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_SIGN = "sign_base_64";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PreSign.db";

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_NAME_USER_ID
            + " integer primary key autoincrement, "
            + COLUMN_NAME_NAME + " text not null,"
            + COLUMN_NAME_LASTNAME +" text not null,"
            + COLUMN_NAME_ADRESS + "text not null,"
            + COLUMN_NAME_CP + "text not null,"
            + COLUMN_NAME_CITY + "text not null,"
            + COLUMN_NAME_TEL + "text not null,"
            + COLUMN_NAME_EMAIL + "text not null,"
            + COLUMN_NAME_PASSWORD + "text not null,"
            + COLUMN_NAME_SIGN + "text not null);" ;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
