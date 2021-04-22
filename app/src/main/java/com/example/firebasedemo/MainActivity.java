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

public class MainActivity extends AppCompatActivity implements ValueEventListener {
//    Place city = new Place("Sochi", 80, 70);

    Place city;

    EditText nameText;
    EditText latText;
    EditText lonText;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText= (EditText) findViewById(R.id.name);
        latText= (EditText) findViewById(R.id.lat);
        lonText= (EditText) findViewById(R.id.lon);

        Place city=new Place(nameText, latText, lonText);
        // получаем ссылку на облачную БД
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("myplace").addValueEventListener(this); // следим за изменением данных
        changePlace(city);


    }
//    public void onInputClick(View v) {
//        String name=nameText.getText().toString();
//        String lat=latText.getText().toString();
//        String lon=lonText.getText().toString();
//
//        Intent i= new Intent(this, Place.class);
//        i.putExtra("name", name);
//        i.putExtra("lat", lat);
//        i.putExtra("lon", lon);
//        startActivity(i);
//    }

    public void changePlace(Place p) {
        dbRef.child("myplace").setValue(p);
       // dbRef.child("myplace").push().setValue(p);


    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Place place = snapshot.getValue(Place.class);
        Log.d("mytag", "key:"+snapshot.getKey());
        Log.d("mytag", "place:"+place);

        /*
       for (DataSnapshot s: snapshot.getChildren() ) {
           Log.d("mytag", "key: " + s.getKey());
           Place place = s.getValue(Place.class);
           Log.d("mytag", "place: " + place);
       }

         */

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
