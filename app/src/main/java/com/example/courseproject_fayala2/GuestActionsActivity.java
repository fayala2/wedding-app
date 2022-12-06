package com.example.courseproject_fayala2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class GuestActionsActivity extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private DataBaseOpenHelper2 dbHelper = null;
    String food = "Your Dish";

    final static String[] all_columns = { DataBaseOpenHelper2.ID, DataBaseOpenHelper2.ITEM, DataBaseOpenHelper2.DESCRIPTION };
    final static String[] print_columns = { DataBaseOpenHelper2.ID, DataBaseOpenHelper2.ITEM, DataBaseOpenHelper2.DESCRIPTION };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_actions);
        dbHelper = new DataBaseOpenHelper2(this);

        final RadioButton chickenButton = (RadioButton)findViewById(R.id.chicken_button);
        final RadioButton salmonButton = (RadioButton)findViewById(R.id.salmon_button);
        final RadioButton steakButton = (RadioButton)findViewById(R.id.steak_button);
        final RadioButton veggieButton = (RadioButton)findViewById(R.id.veggie_button);


        AdapterView.OnClickListener nocl;
        nocl = new AdapterView.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((RadioButton)v).isChecked();
                switch(v.getId()){
                    case R.id.chicken_button:
                        if (checked){
                            food = "Your Dish: Chicken";
                        }
                        break;
                    case R.id.salmon_button:
                        if (checked) {
                            food = "Your Dish: Salmon";
                        }
                        break;
                    case R.id.steak_button:
                        if (checked) {
                            food = "Your Dish: Steak";
                        }
                        break;
                    case R.id.veggie_button:
                        if (checked) {
                            food = "Your Dish: Veggie";
                        }
                        break;
                }
            }
        };

        chickenButton.setOnClickListener(nocl);
        salmonButton.setOnClickListener(nocl);
        steakButton.setOnClickListener(nocl);
        veggieButton.setOnClickListener(nocl);
    }

    public void onResume() {
        super.onResume();

        String myQuery = "SELECT * FROM storagelist";

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(myQuery, null);
        cursor.moveToPosition(0);
        String RSVP = cursor.getString(1);

        cursor.moveToPosition(1);
        String guest_name = cursor.getString(1);

        cursor.moveToPosition(2);
        String requests = cursor.getString(1);

        cursor.moveToPosition(3);
        String dish = cursor.getString(1);

        TextView rsvp_text = (TextView) findViewById(R.id.RSVP);
        rsvp_text.setText(RSVP);

        EditText guest_input = (EditText)findViewById(R.id.guest_name);
        //guest_input.setText(guest_name);
        guest_input.setHint(guest_name);

        EditText requests_input = (EditText)findViewById(R.id.requests);
        //requests_input.setText(requests);
        requests_input.setHint(requests);

        TextView dish_input = (TextView) findViewById(R.id.food);
        dish_input.setText(dish);

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

    public void onMeButtonclicked(View view) {
        Intent intent = new Intent(this, MeActivity.class);

        startActivity(intent);
    }


    public void onRSVPButtonClicked(View view) {
        ContentValues cv = new ContentValues(1);
        String RSVP = "Complete";
        String myQuery = "SELECT * FROM storagelist";

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(myQuery, null);
        cursor.moveToPosition(0);
        String id = cursor.getString(0);
        String currentRSVP = cursor.getString(1);

        if (!currentRSVP.toLowerCase().equals("complete")) {
            TextView rsvp_text = (TextView) findViewById(R.id.RSVP);

            cv.put(DataBaseOpenHelper2.ITEM, RSVP);
            db.update("storagelist", cv, "_id=" + id, null);
            //mCursor.requery();
            //cursor.close();

            rsvp_text.setText(RSVP);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -2);
            toast.show();
        }


        EditText guest_input = (EditText) findViewById(R.id.guest_name);
        String name = guest_input.getText().toString();

        if (name.equals("")) {
            //name = "Guest Name";

        } else {
            cursor.moveToPosition(1);
            id = cursor.getString(0);
            cv.put(DataBaseOpenHelper2.ITEM, name);
            db.update("storagelist", cv, "_id=" + id, null);
            guest_input.setHint(name);
        }


        EditText requests_input = (EditText) findViewById(R.id.requests);
        String request = requests_input.getText().toString();

        if (request.equals("")) {
            //request = "Food Allergies, Favorite Song, etc.";
        } else {
            cursor.moveToPosition(2);
            id = cursor.getString(0);
            cv.put(DataBaseOpenHelper2.ITEM, request);
            db.update("storagelist", cv, "_id=" + id, null);
            requests_input.setHint(request);
        }

        TextView food_display = (TextView)findViewById(R.id.food);
        cursor.moveToPosition(3);
        id = cursor.getString(0);
        cv.put(DataBaseOpenHelper2.ITEM, food);
        db.update("storagelist", cv, "_id="+id, null);
        food_display.setText(food);

        cursor.close();
    }
}