package com.example.courseproject_fayala2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onVenueInfoButtonClicked(View view) {
        Intent intent = new Intent(this, InfoActivity.class);

        startActivity(intent);
    }

    public void onDashboardButtonClicked(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);
    }

    public void onGuestActionsButtonClicked(View view) {
        Intent intent = new Intent(this, GuestActionsActivity.class);

        startActivity(intent);
    }

    public void onMeButtonclicked(View view) {
        Intent intent = new Intent(this, MeActivity.class);

        startActivity(intent);
    }

    public void onSeePhotoButtonClicked(View view) {
        Intent intent = new Intent(this, PhotoListActivity.class);

        startActivity(intent);

    }

    public void onHomeButtonClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void onWeddingPartyButtonClicked(View view) {
        String URL = "https://www.theknot.com/us/kelly-smith-and-michael-bare-oct-2021/wedding-party";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL));
        startActivity(intent);
    }

    public void onTwitterButtonClicked(View view) {
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, "Happy for the Bride and Groom #cs477Wedding");
        tweetIntent.setType("text/plain");

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for(ResolveInfo resolveInfo: resolvedInfoList){
            if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name );
                resolved = true;
                break;
            }
        }
        if(resolved){
            startActivity(tweetIntent);
        }else{
            Toast.makeText(this, "Twitter app isn't found", Toast.LENGTH_LONG).show();
        }
    }

}