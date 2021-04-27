package com.example.firebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputBase extends AppCompatActivity implements ValueEventListener {
    TextView out;
    String name;
    Double lan, lon;
    Place city;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_main);
        out= (TextView) findViewById(R.id.outText);
        Intent i = getIntent();
        String nameStr = i.getStringExtra("name");
        String lanStr = i.getStringExtra("lan");
        String lonStr = i.getStringExtra("lon");

        name= nameStr;
        lan= Double.parseDouble(lanStr);
        lon= Double.parseDouble(lonStr);

        city = new Place(name, lan, lon);

        out.setText("Ваше местоположение: " + name+ "\nКоординаты:"+lan+","+ lon);

        // получаем ссылку на облачную БД
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("myplace").addValueEventListener(this); // следим за изменением данных
        changePlace(city);


    }

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
