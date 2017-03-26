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
import java.util.List;
import java.util.UUID;

public class BeaconDetailsActivity extends AppCompatActivity {

    private TextView beaconInfo;
    private TextView descriptionTextView;
    private Button contactButton;
    private BeaconManager beaconManager;
    private Region region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_details);

        // API call to find out if this is being looked for

        String isHuntedBeacon = getIntent().getStringExtra("isHunted");
        final String macAddress = getIntent().getStringExtra("macAddress");
        final String bounty = getIntent().getStringExtra("bounty");
        final String description = getIntent().getStringExtra("description");
        final String phoneNumber = getIntent().getStringExtra("phoneNumber");

        contactButton = (Button) findViewById(R.id.contactButton);
        if (isHuntedBeacon.equals("no")) {
            contactButton.setVisibility(View.GONE);
        }
        else {
            contactButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchContactInfoActivity(phoneNumber, macAddress);
                    finish();
                }
            });
        }

        beaconInfo = (TextView) findViewById(R.id.beaconInfo);
        if (isHuntedBeacon.equals("yes")) {
            beaconInfo.setText("Someone is looking for this beacon and is offering a $"
                                + bounty + " reward to whoever finds it! If you find the item"
                                + " that matches the description below, contact the user at the"
                                + " phone number provided to claim your reward.");
    }
        else {
            beaconInfo.setText("No one is looking for this beacon.\nMacAddress: " + macAddress);
        }

        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(description);
    }

    private void launchContactInfoActivity(String phoneNumber, String macAddress) {
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("macAddress", macAddress);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
