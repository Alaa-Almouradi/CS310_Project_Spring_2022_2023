package com.example.istview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class WriteCommentActivity extends AppCompatActivity {

    TextView txtname;
    EditText edttxtComment;
    RadioGroup rdbtn;
    Button postbtn;
    int rating;
    Locations location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        location = (Locations) getIntent().getExtras().getSerializable("location");

        txtname = findViewById(R.id.textViewWriteName);
        txtname.setText("Write Comment on: " + location.getName());
        edttxtComment = findViewById(R.id.editTextComment);
        rdbtn = findViewById(R.id.radioGroup);
        postbtn = findViewById(R.id.buttonPost);

        postbtn.setOnClickListener(v->{
            RadioButton rd = findViewById(rdbtn.getCheckedRadioButtonId());
            rating = Integer.parseInt(rd.getText().toString());
            CommentsRepository repo = new CommentsRepository();
            repo.postComment(((ISTViewApplication)getApplication()).srv, txtname.getText().toString(),edttxtComment.getText().toString(),(double) rating, location);
            finish();
        });






    }
}