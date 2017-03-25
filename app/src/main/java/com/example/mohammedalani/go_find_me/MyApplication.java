package com.example.mohammedalani.go_find_me;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;

/**
 * Created by mohammedalani on 2017-03-25.
 */

public class MyApplication extends Application {

    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(getApplicationContext());

    }
}