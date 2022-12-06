package com.example.courseproject_fayala2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper2 extends SQLiteOpenHelper {
    final static String DBNAME = "storagelist";
    final static String ID = "_id";
    final static String ITEM = "item";
    final static String DESCRIPTION = "description";
    final private static String CREATE_CMD =
            "CREATE TABLE "+DBNAME+" (" + ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ITEM + " TEXT NOT NULL, "+
                    DESCRIPTION +" TEXT NOT NULL )";

    final private static Integer VERSION = 1;
    final private Context context;

    public DataBaseOpenHelper2(Context context) {
        super(context, "storagelist", null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);

        ContentValues values = new ContentValues();
        //0
        values.put(ITEM, "Incomplete");
        values.put(DESCRIPTION, "RSVP");
        db.insert(DBNAME, null, values);

        //1
        values.put(ITEM, "Your Guest");
        values.put(DESCRIPTION, "Guest Name");
        db.insert(DBNAME, null, values);

        //2
        values.put(ITEM, "Food Allergies, Favorite Song, etc.");
        values.put(DESCRIPTION, "Requests");
        db.insert(DBNAME, null, values);

        //3
        values.put(ITEM, "Your Food");
        values.put(DESCRIPTION, "Dish");
        db.insert(DBNAME, null, values);

        //4
        values.put(ITEM, "Your Name");
        values.put(DESCRIPTION, "Name");
        db.insert(DBNAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
