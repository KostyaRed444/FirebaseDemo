package com.example.firebasedemo;

import android.content.Intent;
import android.widget.EditText;

public class Place {
    public String name;
    public double lan, lon;

    public Place(EditText nameText, EditText latText, EditText lonText) {
        // для использования с Firebase требуется конструктор по умолчанию
    }
    public Place(String name, double lan, double lon) {
        this.name = name;
        this.lan = lan;
        this.lon = lon;
    }
    public String toString () {
        return "name: " + name + ", latitude: " + lan + ", longtitude: " + lon;
    }
}
