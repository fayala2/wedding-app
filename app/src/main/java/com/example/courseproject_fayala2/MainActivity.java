package com.example.courseproject_fayala2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private DataBaseOpenHelper2 dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DataBaseOpenHelper2(this);
    }

    public void onDashboardButtonClicked(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);
    }

    public void onMeButtonclicked(View view) {
        Intent intent = new Intent(this, MeActivity.class);

        startActivity(intent);
    }

    public void onResume(){
        super.onResume();

        String myQuery = "SELECT * FROM storagelist";

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(myQuery, null);

        cursor.moveToPosition(1);
        String guest_name = cursor.getString(1);
        String yourguest = "Your Guest: " + guest_name;

        TextView guest = (TextView)findViewById(R.id.guest);
        guest.setText(yourguest);

        cursor.moveToPosition(4);
        String name = cursor.getString(1);
        String attending = "Attending: " + name;

        TextView attendee = (TextView)findViewById(R.id.attending);
        attendee.setText(attending);

    }
}