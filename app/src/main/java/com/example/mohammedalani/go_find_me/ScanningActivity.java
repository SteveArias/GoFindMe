package com.example.mohammedalani.go_find_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScanningActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private Region region;
    private Button stopScanButton;
    private TextView scanningMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanning);

        scanningMessage = (TextView) findViewById(R.id.scanningMessage);
        scanningMessage.setText("Now scanning for nearby beacons...");

        stopScanButton = (Button) findViewById(R.id.stopScanButton);
        stopScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        beaconManager = new BeaconManager(this);
        region = new Region("ranged region", null, null, null);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    // API call to check if beacon is being looked for
                    int listSize = list.size();
                    ArrayList<Beacon> beaconsFoundList = new ArrayList<Beacon>();
                    for (int i = 0; i < listSize; ++i) {
                        beaconsFoundList.add(list.get(i));
                    }
                    launchBeaconsFoundActivity(beaconsFoundList);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);

        super.onPause();
    }

    private void launchBeaconsFoundActivity(ArrayList<Beacon> beaconsFoundList) {
        Intent intent = new Intent(this, BeaconsFoundActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("beaconList", beaconsFoundList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
