package com.example.istview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetails extends AppCompatActivity {

    ImageView imgLoc;
    TextView txtName;
    TextView txtdesc;
    Button btnComments;



    Handler imgHandler= new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            imgLoc.setImageBitmap((Bitmap)msg.obj);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imgLoc = findViewById(R.id.imageViewLoc);
        txtName = findViewById(R.id.textViewName);
        txtdesc = findViewById(R.id.textViewDescription);
        btnComments = findViewById(R.id.buttonComments);

        btnComments.setOnClickListener(v->{

            //Go to comments activity

        });


        Locations location = (Locations) getIntent().getExtras().getSerializable("location");
        LocationsRepository repo = new LocationsRepository();
        repo.downloadImage(((ISTViewApplication)getApplication()).srv,imgHandler,location.getImage());
        txtName.setText(location.getName());
        txtdesc.setText(location.getDescription());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}