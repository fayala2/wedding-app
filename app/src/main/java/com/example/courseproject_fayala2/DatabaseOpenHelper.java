package com.example.courseproject_fayala2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    final static String DBNAME = "photolist";
    final static String ID = "_id";
    final static String PHOTO = "photo";
    //final static String ISDONE = "isdone";
    final private static String CREATE_CMD =
            "CREATE TABLE "+DBNAME+" (" + ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PHOTO + " BLOB)";//+
                    //ISDONE +" TEXT NOT NULL )";

    final private static Integer VERSION = 1;
    final private Context context;

    public DatabaseOpenHelper(Context context) {
        super(context, "photolist", null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
        //ContentValues values = new ContentValues();

        /*
        values.put(ITEM, "Wash the car");
        values.put(ISDONE, "not done");
        db.insert(DBNAME,null,values);
        values.put(ITEM, "take out trash");
        values.put(ISDONE, "not done");
        db.insert(DBNAME,null,values);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    void deleteDatabase ( ) {
        context.deleteDatabase(DBNAME);
    }
}



