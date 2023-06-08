package com.example.istview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recView;
    ProgressBar prgBar;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Locations> data = (List<Locations>) msg.obj;
            LocationAdapter adp =
                    new LocationAdapter(MainActivity.this, data);
            recView.setAdapter(adp);

            prgBar.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prgBar = findViewById(R.id.progressBarList);
        recView = findViewById(R.id.recyclerViewList);
        recView.setLayoutManager(new LinearLayoutManager(this));
        prgBar.setVisibility(View.VISIBLE);
        LocationsRepository repo = new LocationsRepository();
        //repo.getAllLocations(((ISTViewApplication)getApplication()).srv, handler);
        repo.locationByCategory(((ISTViewApplication)getApplication()).srv, handler, "hOT spOt");
    }
}