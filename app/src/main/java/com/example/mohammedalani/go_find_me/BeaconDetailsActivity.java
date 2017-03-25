package com.example.mohammedalani.go_find_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import java.util.List;
import java.util.UUID;

public class BeaconDetailsActivity extends AppCompatActivity {

    private TextView beaconInfo;
    private BeaconManager beaconManager;
    private Region region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_details);

        final String macAddress = getIntent().getStringExtra("macAddress");
        final String beaconUUID = getIntent().getStringExtra("UUID");

        beaconInfo = (TextView) findViewById(R.id.beaconInfo);
        beaconInfo.setText("MacAddress: " + macAddress + "\n\n" + "UUID: " + beaconUUID);

    }
}
