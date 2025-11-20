package com.example.a14_ejerciciogif;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
public class MainActivity extends AppCompatActivity {

    ImageView img1, img2, img3, img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.imagen1);
        img2 = findViewById(R.id.imagen2);
        img3 = findViewById(R.id.imagen3);
        img4 = findViewById(R.id.imagen4);

        Glide.with(this).load(R.drawable.usa).into(img1);
        Glide.with(this).load(R.drawable.cee).into(img2);
        Glide.with(this).load(R.drawable.japon).into(img3);
        Glide.with(this).load(R.drawable.ingla).into(img4);

    }
}