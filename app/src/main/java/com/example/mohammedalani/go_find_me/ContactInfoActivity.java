package com.example.mohammedalani.go_find_me;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactInfoActivity extends AppCompatActivity {

    private TextView contactInfoTextView;
    private Button contactInfoOkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);

        contactInfoTextView = (TextView) findViewById(R.id.contactInfoTextView);
        contactInfoTextView.setText("Thank you for finding this beacon, the user's phone number is "
                                    + getIntent().getStringExtra("phoneNumber"));

        contactInfoOkButton = (Button) findViewById(R.id.contactInfoOkButton);
        contactInfoOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToBeaconsFoundActivity();
            }
        });
    }

    private void goBackToBeaconsFoundActivity() {
        finish();
    }
}
