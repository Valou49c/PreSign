package com.iut.couchut.lebouche.presign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentin on 02/02/2015.
 */
public class PreSignDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_NAME_USER_ID,
            MySQLiteHelper.COLUMN_NAME_NAME,
            MySQLiteHelper.COLUMN_NAME_LASTNAME,
            MySQLiteHelper.COLUMN_NAME_ADRESS,
            MySQLiteHelper.COLUMN_NAME_CP,
            MySQLiteHelper.COLUMN_NAME_CITY,
            MySQLiteHelper.COLUMN_NAME_TEL,
            MySQLiteHelper.COLUMN_NAME_EMAIL,
            MySQLiteHelper.COLUMN_NAME_PASSWORD,
            MySQLiteHelper.COLUMN_NAME_SIGN};

    public PreSignDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Client createUser(String nomCli, String prenomCli, String adresse,
                             String cp, String ville, String tel, String emailCli, String passwordCli, String signatureBase64) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_NAME, nomCli);
        values.put(MySQLiteHelper.COLUMN_NAME_LASTNAME, prenomCli);
        values.put(MySQLiteHelper.COLUMN_NAME_ADRESS, adresse);
        values.put(MySQLiteHelper.COLUMN_NAME_CP, cp);
        values.put(MySQLiteHelper.COLUMN_NAME_CITY, ville);
        values.put(MySQLiteHelper.COLUMN_NAME_TEL, tel);
        values.put(MySQLiteHelper.COLUMN_NAME_EMAIL, emailCli);
        values.put(MySQLiteHelper.COLUMN_NAME_PASSWORD, passwordCli);
        values.put(MySQLiteHelper.COLUMN_NAME_SIGN, signatureBase64);

        long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, MySQLiteHelper.COLUMN_NAME_USER_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Client newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUsers(Client user) {
        String id = user.getIdCli();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_USERS, MySQLiteHelper.COLUMN_NAME_USER_ID
                + " = " + id, null);
    }

    public List<Client> getAllUser() {
        List<Client> users = new ArrayList<Client>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client client = cursorToUser(cursor);
            users.add(client);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }


    private Client cursorToUser(Cursor cursor) {
        Client user = new Client();
        user.setIdCli(cursor.getString(0));
        user.setNomCli(cursor.getString(1));
        user.setPrenomCli(cursor.getString(2));
        user.setAdresse(cursor.getString(3));
        user.setCp(cursor.getString(4));
        user.setVille(cursor.getString(5));
        user.setTel(cursor.getString(6));
        user.setEmailCli(cursor.getString(7));
        user.setPasswordCli(cursor.getString(8));
        user.setSignature_Base64(cursor.getString(9));
        return user;
    }
}



//    public static abstract class UserEntry implements BaseColumns {
//
//
//
//
//
//        public static final String TABLE_USERS = "users";
//        public static final String COLUMN_NAME_USER_ID = "userid";
//        public static final String COLUMN_NAME_NAME = "name";
//        public static final String COLUMN_NAME_LASTNAME = "lastname";
//        public static final String COLUMN_NAME_ADRESS = "adress";
//        public static final String COLUMN_NAME_CP = "CP";
//        public static final String COLUMN_NAME_CITY = "city";
//        public static final String COLUMN_NAME_TEL = "mobile";
//        public static final String COLUMN_NAME_EMAIL = "email";
//        public static final String COLUMN_NAME_PASSWORD = "password";
//        public static final String COLUMN_NAME_SIGN = "sign_base_64";
//
//        private static final String TEXT_TYPE = " TEXT";
//        private static final String COMMA_SEP = ",";
//        private static final String SQL_CREATE_ENTRIES =
//                "CREATE TABLE " + UserEntry.TABLE_USERS + " (" +
//                        UserEntry._ID + " INTEGER PRIMARY KEY," +
//                        UserEntry.COLUMN_NAME_USER_ID + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_LASTNAME + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_ADRESS + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_CP + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_CITY + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_TEL + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
//                        UserEntry.COLUMN_NAME_SIGN + TEXT_TYPE + COMMA_SEP +
//                        " )";
//
//        private static final String SQL_DELETE_ENTRIES =
//                "DROP TABLE IF EXISTS " + UserEntry.TABLE_USERS;
//
//        public class UserReaderDbHelper extends SQLiteOpenHelper {
//            // If you change the database schema, you must increment the database version.
//            public static final int DATABASE_VERSION = 1;
//            public static final String DATABASE_NAME = "PreSign.db";
//
//            public UserReaderDbHelper(Context context) {
//                super(context, DATABASE_NAME, null, DATABASE_VERSION);
//            }
//            public void onCreate(SQLiteDatabase db) {
//                db.execSQL(SQL_CREATE_ENTRIES);
//            }
//            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                // This database is only a cache for online data, so its upgrade policy is
//                // to simply to discard the data and start over
//                db.execSQL(SQL_DELETE_ENTRIES);
//                onCreate(db);
//            }
//            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                onUpgrade(db, oldVersion, newVersion);
//            }
//        }
//        // Database fields
//
//
//
//    }
//}
