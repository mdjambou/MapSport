package com.team.mapsport.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DoItNowOpenHelper extends SQLiteOpenHelper{
   
    static final String DATABASE_NAME = "dbApp.db";
    private static final int DATABASE_VERSION =1;

    static final String USER_TABLE = "User";    
    static final String KEY_USER_COLUMN_IDENTIFIANT = "ID";
    public static final int NUM_COLUMN_IDENTIFIANT = 0;
    static final String KEY_USER_COLUMN_FIRST_NAME = "firstName";
    public static final int NUM_COLUMN_FIRST_NAME = 1;
    static final String KEY_USER_COLUMN_LAST_NAME = "lastName";
    public static final int NUM_COLUMN_LAST_NAME = 2;
    static final String KEY_USER_COLUMN_PSEUDO = "pseudo";
    public static final int NUM_COLUMN_PSEUDO = 3;
    static final String KEY_USER_COLUMN_PASSWORD = "password";
    public static final int NUM_COLUMN_PASSWORD = 4;
    static final String KEY_USER_COLUMN_PICTURE= "picture";
    public static final int NUM_COLUMN_PICTURE = 5;
    static final String KEY_USER_COLUMN_EMAIL = "email";
    public static final int NUM_COLUMN_EMAIL = 6;
    static final String KEY_USER_COLUMN_CELLNUMBER = "cellNumber";
    public static final int NUM_COLUMN_CELLNUMBER = 7;
    static final String KEY_USER_COLUMN_CITY = "city";
    public static final int NUM_COLUMN_CITY = 8;
    static final String KEY_USER_COLUMN_COUNTRY = "country";
    public static final int NUM_COLUMN_COUNTRY = 9;

    private static final String USER_CREATE = "CREATE TABLE " 
            + USER_TABLE + " (" + KEY_USER_COLUMN_IDENTIFIANT
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USER_COLUMN_FIRST_NAME + " TEXT NOT NULL, "
            + KEY_USER_COLUMN_LAST_NAME + " TEXT NOT NULL, "
            + KEY_USER_COLUMN_PSEUDO + " TEXT NOT NULL, "
            + KEY_USER_COLUMN_PASSWORD + " TEXT NOT NULL, "
            + KEY_USER_COLUMN_PICTURE + " BLOB, "
            + KEY_USER_COLUMN_EMAIL + " TEXT NOT NULL, "
            + KEY_USER_COLUMN_CITY + " TEXT, "
            + KEY_USER_COLUMN_COUNTRY + " TEXT, "
            + KEY_USER_COLUMN_CELLNUMBER + " INTEGER);";

    static final String CATEGORY_TABLE = "Category";
    static final String KEY_CATEGORY_COLUMN_ID = "ID";
    public static final int NUM_COLUMN_CATEGORY_ID = 0;
    static final String KEY_CATEGORY_COLUMN_NAME = "name";
    public static final int NUM_COLUMN_CATEGORY_NAME = 1;

    private static final String CATEGORY_CREATE = "CREATE TABLE "
            + CATEGORY_TABLE + " (" + KEY_CATEGORY_COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CATEGORY_COLUMN_NAME + " INTEGER NOT NULL);";

    /*static final String LOCATION_TABLE = "Location";
    static final String KEY_LOCATION_COLUMN_ID = "ID";
    public static final int NUM_COLUMN_LOCATION_ID = 1;
    static final String KEY_LOCATION_COLUMN_NAME = "name";
    public static final int NUM_COLUMN_LOCATION_NAME = 2;
    static final String KEY_LOCATION_COLUMN_ADDRESS = "address";
    public static final int NUM_COLUMN_LOCATION_ADDRESS = 3;

    private static final String LOCATION_CREATE = "CREATE TABLE "
            + LOCATION_TABLE + " (" + KEY_LOCATION_COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LOCATION_COLUMN_NAME + " INTEGER NOT NULL, "
            + KEY_LOCATION_COLUMN_ADDRESS + " INTEGER NOT NULL);";

*/
    static final String EVENT_TABLE = "Event";
    public static final String KEY_EVENT_COLUMN_ID = "ID";
    public static final int NUM_COLUMN_EVENT_ID = 0;
    static final String KEY_EVENT_COLUMN_NAME = "name";
    public static final int NUM_COLUMN_EVENT_NAME = 1;
    static final String KEY_EVENT_COLUMN_CATEGORY = "idCategory";
    public static final int NUM_COLUMN_EVENT_CATEGORY = 2;
    static final String KEY_EVENT_COLUMN_PICTURE = "picture";
    public static final int NUM_COLUMN_EVENT_PICTURE = 3;
    static final String KEY_EVENT_COLUMN_PRIVATE = "private";
    public static final int NUM_COLUMN_EVENT_PRIVATE = 4;
    static final String KEY_EVENT_COLUMN_SIZE = "size";
    public static final int NUM_COLUMN_EVENT_SIZE = 5;
    static final String KEY_EVENT_COLUMN_NB_PARTICIPANT = "nbParticipant";
    public static final int NUM_COLUMN_EVENT_NB_PARTICIPANT = 6;
    static final String KEY_EVENT_COLUMN_DESCRIPTION = "description";
    public static final int NUM_COLUMN_EVENT_DESCRIPTION = 7;
    static final String KEY_EVENT_COLUMN_LOCATION = "location";
    public static final int NUM_COLUMN_EVENT_LOCATION = 8;
    static final String KEY_EVENT_COLUMN_STARTING_DATE = "startingDate";
    public static final int NUM_COLUMN_EVENT_STARTING_DATE = 9;
    static final String KEY_EVENT_COLUMN_CLOSING_DATE = "closingDate";
    public static final int NUM_COLUMN_EVENT_CLOSING_DATE = 10;
    static final String KEY_EVENT_COLUMN_STATUS = "status";
    public static final int NUM_COLUMN_EVENT_STATUS = 11;

    private static final String EVENT_CREATE = "CREATE TABLE "
            + EVENT_TABLE + " (" + KEY_EVENT_COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_EVENT_COLUMN_NAME + " TEXT NOT NULL, "
            + KEY_EVENT_COLUMN_CATEGORY + " INTEGER NOT NULL, "
            + KEY_EVENT_COLUMN_PICTURE + " BLOB, "
            + KEY_EVENT_COLUMN_PRIVATE + " INTEGER NOT NULL, "  //0 false et 1 true
            + KEY_EVENT_COLUMN_SIZE + " INTEGER NOT NULL, "
            + KEY_EVENT_COLUMN_NB_PARTICIPANT + " INTEGER NOT NULL, "
            + KEY_EVENT_COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + KEY_EVENT_COLUMN_LOCATION + " TEXT NOT NULL, "
            + KEY_EVENT_COLUMN_STARTING_DATE + " TEXT NOT NULL, "
            + KEY_EVENT_COLUMN_CLOSING_DATE + " TEXT NOT NULL, "
            + KEY_EVENT_COLUMN_STATUS + " INTEGER NOT NULL);";
   
    static final String PARTICIPANT_TABLE = "Participant";
    static final String KEY_PARTICIPANT_COLUMN_ID = "ID";
    public static final int NUM_COLUMN_PARTICIPANT_ID = 0;
    static final String KEY_PARTICIPANT_COLUMN_USER = "user";
    public static final int NUM_COLUMN_PARTICIPANT_USER = 1;
    static final String KEY_PARTICIPANT_COLUMN_EVENT = "event";
    public static final int NUM_COLUMN_PARTICIPANT_EVENT = 2;
    static final String KEY_PARTICIPANT_COLUMN_TYPE = "type";
    public static final int NUM_COLUMN_PARTICIPANT_TYPE = 3;
    static final String KEY_PARTICIPANT_COLUMN_ANSWER = "answer";
    public static final int NUM_COLUMN_PARTICIPANT_ANSWER = 4;

    private static final String PARTICIPANT_CREATE = "CREATE TABLE "
            + PARTICIPANT_TABLE + " (" + KEY_PARTICIPANT_COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_PARTICIPANT_COLUMN_USER + " INTEGER NOT NULL, "
            + KEY_PARTICIPANT_COLUMN_EVENT + " INTEGER NOT NULL, "
            + KEY_PARTICIPANT_COLUMN_TYPE + " TEXT NOT NULL, "
            + KEY_PARTICIPANT_COLUMN_ANSWER + " TEXT NOT  NULL);";

    DoItNowOpenHelper (Context context, CursorFactory factory){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }
    
    
    @Override
    public void onCreate (SQLiteDatabase db){
        Log.v("INFO1", "creating db");
        db.execSQL(CATEGORY_CREATE);
        db.execSQL(USER_CREATE);
        db.execSQL(EVENT_CREATE);
        db.execSQL(PARTICIPANT_CREATE);
        String[] tabCategory = {"Culture", "Loisirs", "Musique", "Sorties", "Sport"};
        for (int i = 0; i<tabCategory.length;i++) {
            String CATEGORY_INSERT = "INSERT INTO "
                    + CATEGORY_TABLE + " (" + KEY_CATEGORY_COLUMN_NAME + ") VALUES ( '" + tabCategory[i] + "' );";
            db.execSQL(CATEGORY_INSERT);
        }
    }
    
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w("TaskDBAdapter", "Mise à jour de la version" +
              oldVersion+ " vers la version " +
              newVersion+ ", ce qui detruira toutes les anciennes donnees");
        db.execSQL("DROP TABLE IF EXISTS  " + PARTICIPANT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS android_metadata");

        onCreate(db);
    }

}

