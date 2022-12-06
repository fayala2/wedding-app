package com.example.courseproject_fayala2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MeActivity extends AppCompatActivity {

    //String food = "Your Dish";

    //final static String[] all_columns = { DataBaseOpenHelper2.ID, DataBaseOpenHelper2.ITEM, DataBaseOpenHelper2.DESCRIPTION };
    //final static String[] print_columns = { DataBaseOpenHelper2.ID, DataBaseOpenHelper2.ITEM, DataBaseOpenHelper2.DESCRIPTION };

    private SQLiteDatabase db = null;
    private DataBaseOpenHelper2 dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        dbHelper = new DataBaseOpenHelper2(this);
    }

    public void onResume() {
        super.onResume();

        String myQuery = "SELECT * FROM storagelist";

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(myQuery, null);

        cursor.moveToPosition(4);
        String name = cursor.getString(1);

        EditText name_input = (EditText)findViewById(R.id.name_input);
        //guest_input.setText(guest_name);
        name_input.setHint(name);

        cursor.close();
    }

    public void onUpdateButtonClicked(View view) {
        EditText name_input = (EditText)findViewById(R.id.name_input);
        String name = name_input.getText().toString();

        ContentValues cv = new ContentValues(1);
        String myQuery = "SELECT * FROM storagelist";

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(myQuery, null);
        cursor.moveToPosition(4);
        String id = cursor.getString(0);

        if (name.equals("")) {

        } else {
            cv.put(DataBaseOpenHelper2.ITEM, name);
            db.update("storagelist", cv, "_id="+id, null);
            name_input.setHint(name);
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, -2);
        toast.show();

        cursor.close();
    }

    public void onDashboardButtonClicked(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);
    }


    public void onHomeButtonClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}