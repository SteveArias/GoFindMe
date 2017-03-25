package com.example.mohammedalani.go_find_me;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.estimote.sdk.Beacon;

import java.util.ArrayList;

public class BeaconsFoundActivity extends AppCompatActivity {

    private ListView beaconsFoundListView;
    private TextView beaconsFoundMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacons_found);

        Bundle bundle = getIntent().getExtras();
        final ArrayList<Beacon> beaconsFoundList = bundle.getParcelableArrayList("beaconList");

        beaconsFoundMessage = (TextView) findViewById(R.id.beaconsFoundMessage);
        if (beaconsFoundList.size() == 1) {
            beaconsFoundMessage.setText("We found " + beaconsFoundList.size() + " beacon near you!");
        }
        else {
            beaconsFoundMessage.setText("We found " + beaconsFoundList.size() + " beacons near you!");
        }

        beaconsFoundListView = (ListView) findViewById(R.id.beaconsFoundListView);
        String[] listItems = new String[beaconsFoundList.size()];
        for(int i = 0; i < beaconsFoundList.size(); i++){
            Beacon beacon = beaconsFoundList.get(i);
            listItems[i] = beacon.getMacAddress().toString();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        beaconsFoundListView.setAdapter(adapter);

        final Context context = this;
        beaconsFoundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1
                Beacon beacon = beaconsFoundList.get(position);

                Intent intent = new Intent(context, BeaconDetailsActivity.class);

                intent.putExtra("macAddress", beacon.getMacAddress().toString());
                intent.putExtra("beaconUUID", beacon.getProximityUUID().toString());

                startActivity(intent);
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
