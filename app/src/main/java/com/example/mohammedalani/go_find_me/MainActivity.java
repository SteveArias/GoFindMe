package com.example.mohammedalani.go_find_me;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.service.internal.RegionObserver;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button launchScanningActivityButton;
    private Button findBeaconButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView introMessage=(TextView)findViewById(R.id.introMessage);
        introMessage.setText("Welcome to GoFindMe!\nTo start scanning for beacons, \ntap the scan button below.");

        launchScanningActivityButton = (Button) findViewById(R.id.scanButton);
        launchScanningActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchScanningActivity();
            }
        });

        findBeaconButton = (Button) findViewById(R.id.findBeaconButton);
        findBeaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchEnterLostBeaconInfoActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void launchScanningActivity() {
        Intent intent = new Intent(this, ScanningActivity.class);
        startActivity(intent);
    }

    private void launchEnterLostBeaconInfoActivity() {
        Intent intent = new Intent(this, EnterLostBeaconInfoActivity.class);
        startActivity(intent);
    }
}
