package com.example.courseproject_fayala2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void onDashboardButtonClicked(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);
    }

    public void onMeButtonclicked(View view) {
        Intent intent = new Intent(this, MeActivity.class);

        startActivity(intent);
    }

    public void onGetDirectionsButtonClicked(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        Button mapButton = (Button)findViewById(R.id.map);

        //intent.putExtra(MapsActivity.LOCATION, location);
         startActivity(intent);

    }

    public void onHomeButtonClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void onWebsiteButtonClicked(View view) {
        String URL = "https://www.wineryatbullrun.com/Events/Weddings";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL));
        startActivity(intent);
    }
}