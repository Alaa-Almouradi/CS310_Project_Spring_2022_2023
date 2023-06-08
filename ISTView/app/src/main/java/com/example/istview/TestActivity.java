package com.example.istview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    RecyclerView recView;
    ProgressBar prgBar;

    CommentsRepository repo;

    Locations loc = new Locations("645708d9b1d1496b8debeff5", "Viaport AVM", "car", 200, "ds", "Asfdfs", "asdd");


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Comments> data = (List<Comments>) msg.obj;
            CommentsAdapter adp =
                    new CommentsAdapter(TestActivity.this, data);
            recView.setAdapter(adp);

            prgBar.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //tabLayout = findViewById(R.id.tabLayout_main);

        prgBar = findViewById(R.id.progressBarList2);
        recView = findViewById(R.id.recyclerViewListComment);
        recView.setLayoutManager(new LinearLayoutManager(this));

        prgBar.setVisibility(View.VISIBLE);

        repo = new CommentsRepository();
        //repo.getAllLocations(((ISTViewApplication)getApplication()).srv, handler);
        repo.commentByLocation(((ISTViewApplication)getApplication()).srv, handler, loc);

    }
}
