package com.example.mohammedalani.go_find_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterLostBeaconInfoActivity extends AppCompatActivity {

    private EditText editTextMacAddress;
    private EditText editTextPhoneNumber;
    private EditText editTextBounty;
    private EditText editTextDescription;
    private Button beaconInfoSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_lost_beacon_info);

        beaconInfoSaveButton = (Button) findViewById(R.id.beaconInfoSaveButton);
        beaconInfoSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextMacAddress = (EditText) findViewById(R.id.editTextMacAddress);
                editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
                editTextBounty = (EditText) findViewById(R.id.editBounty);
                editTextMacAddress = (EditText) findViewById(R.id.editTextDescription);

                // send info to server

                launchFindBeaconValidationActivity();
            }
        });

    }

    private void launchFindBeaconValidationActivity() {
        Intent intent = new Intent(this, FindBeaconValidationActivity.class);
        startActivity(intent);
    }
}
