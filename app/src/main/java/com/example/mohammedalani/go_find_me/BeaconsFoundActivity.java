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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeaconsFoundActivity extends AppCompatActivity {

    private ListView beaconsFoundListView;
    private TextView beaconsFoundMessage;
    private List<HuntedBeacon> huntedBeacons = new ArrayList<HuntedBeacon>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacons_found);

        //getHuntedBeaconList();

        Bundle bundle = getIntent().getExtras();
        final ArrayList<Beacon> beaconsFoundList = bundle.getParcelableArrayList("beaconList");

        beaconsFoundMessage = (TextView) findViewById(R.id.beaconsFoundMessage);
        String message = "";
        if (beaconsFoundList.size() == 1) {
            message += ("We found " + beaconsFoundList.size() + " beacon near you!");
        }
        else {
            message += ("We found " + beaconsFoundList.size() + " beacons near you!");
        }
        if (huntedBeacons.size() > 0) {
            if (huntedBeacons.size() == 1) {
                message += ("Also, someone is looking for 1 of them!");
            }
            else {
                message += ("Also, someone is looking for " + huntedBeacons.size() + " of them!");
            }
        }
        beaconsFoundMessage.setText(message);
        beaconsFoundListView = (ListView) findViewById(R.id.beaconsFoundListView);
        String[] listItems = new String[beaconsFoundList.size()];
//        for(int i = 0; i < huntedBeacons.size(); i++){
//            HuntedBeacon huntedBeacon = huntedBeacons.get(i);
//            boolean isHunted = false;
//            for (int j = 0; j < beaconsFoundList.size(); ++j) {
//                Beacon beacon = beaconsFoundList.get(j);
//                if (huntedBeacon.getMacAddress().equals(beacon.getMacAddress())) {
//                    isHunted = true;
//                    break;
//                }
//            }
//            if (!isHunted) {
//                huntedBeacons.remove(i);
//                --i;
//            }
//        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        beaconsFoundListView.setAdapter(adapter);

        final Context context = this;
        beaconsFoundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Beacon beacon = beaconsFoundList.get(position);
                boolean isHunted = false;
                int huntedBeaconIndex = -1;

                Intent intent = new Intent(context, BeaconDetailsActivity.class);

                for (int i = 0; i < huntedBeacons.size(); ++i) {
                    if (huntedBeacons.get(i).getMacAddress().equals(beacon.getMacAddress())) {
                        isHunted = true;
                        huntedBeaconIndex = i;
                        break;
                    }
                }

                if (isHunted) {
                    intent.putExtra("isHunted", "yes");
                    intent.putExtra("phoneNumber", huntedBeacons.get(huntedBeaconIndex).getPhoneNumber());
                    intent.putExtra("description", huntedBeacons.get(huntedBeaconIndex).getDescription());
                    intent.putExtra("bounty", huntedBeacons.get(huntedBeaconIndex).getBounty());
                }
                else {
                    intent.putExtra("isHunted", "no");
                }
                intent.putExtra("macAddress", beacon.getMacAddress().toString());

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

    private void getHuntedBeaconList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://secure-coast-11923.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GoFindMeService goFindMeService = retrofit.create(GoFindMeService.class);
        Call<List<HuntedBeacon>> call = goFindMeService.getHuntedBeacons();

        call.enqueue(new Callback<List<HuntedBeacon>>() {
            @Override
            public void onResponse(Call<List<HuntedBeacon>> call, Response<List<HuntedBeacon>> response) {
                huntedBeacons = response.body();
            }

            @Override
            public void onFailure(Call<List<HuntedBeacon>> call, Throwable t) {
                huntedBeacons = new ArrayList<HuntedBeacon>();
            }
        });
    }
}
