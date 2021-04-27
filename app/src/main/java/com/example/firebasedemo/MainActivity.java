package com.example.firebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText latText;
    EditText lonText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText= (EditText) findViewById(R.id.name);
        latText= (EditText) findViewById(R.id.lat);
        lonText= (EditText) findViewById(R.id.lon);


    }
    public void onInputClick(View v) {
        String name=nameText.getText().toString();
        String lat=latText.getText().toString();
        String lon=lonText.getText().toString();

        Intent i= new Intent(this, InputBase.class);
        i.putExtra("name", name);
        i.putExtra("lat", lat);
        i.putExtra("lon", lon);
        startActivity(i);
    }

    


}
