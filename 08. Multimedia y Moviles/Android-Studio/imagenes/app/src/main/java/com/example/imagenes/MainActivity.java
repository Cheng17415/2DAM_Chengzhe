package com.example.imagenes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView img1, img2, img3, img4;
    EditText DE,DY,DL;
    double VDE = 0.86,VDY = 160.56 ,VDL = 0.76;
    TextView ED, EY, EL, YD, YE, YL, LD, LE, LY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        img1=findViewById(R.id.imagen1);
        img2=findViewById(R.id.imagen2);
        img3=findViewById(R.id.imagen3);
        img4=findViewById(R.id.imagen4);
        img4=findViewById(R.id.imagen4);

        DE = findViewById(R.id.de);
        DY = findViewById(R.id.dy);
        DL = findViewById(R.id.dl);

        ED = findViewById(R.id.ed);
        EY = findViewById(R.id.ey);
        EL = findViewById(R.id.el);

        YD = findViewById(R.id.yd);
        YE = findViewById(R.id.ye);
        YL = findViewById(R.id.yl);

        LD = findViewById(R.id.ld);
        LE = findViewById(R.id.le);
        LY = findViewById(R.id.ly);

        Glide.with(this).load(R.drawable.usa).into(img1);
        Glide.with(this).load(R.drawable.cee).into(img2);
        Glide.with(this).load(R.drawable.japon).into(img3);
        Glide.with(this).load(R.drawable.ingla).into(img4);

        cambio();
        /**********************************************************************************************/
        DE.setOnEditorActionListener((v, actionId, event) -> {
            VDE = !DE.getText().toString().isEmpty() ? Double.parseDouble(DE.getText().toString()): 0.0;
            cambio();
            return false;
        });
        /**********************************************************************************************/
        DY.setOnEditorActionListener((v, actionId, event) -> {
            VDY = !DY.getText().toString().isEmpty() ? Double.parseDouble(DY.getText().toString()): 0.0;
            cambio();
            return false;
        });
        /**********************************************************************************************/
        DL.setOnEditorActionListener((v, actionId, event) -> {
            VDL = !DL.getText().toString().isEmpty() ? Double.parseDouble(DL.getText().toString()): 0.0;
            cambio();
            return false;
        });
        /**********************************************************************************************/
    }

    @SuppressLint("DefaultLocale")
    public void cambio(){
        DE.setText(String.valueOf(VDE));
        DY.setText(String.valueOf(VDY));
        DL.setText(String.valueOf(VDL));

        ED.setText(String.format("%.6f", 1/VDE));
        YD.setText(String.format("%.6f", 1/VDY));
        LD.setText(String.format("%.6f", 1/VDL));

        EY.setText(String.format("%.6f", VDY/VDE));
        EL.setText(String.format("%.6f", VDL/VDE));
        YL.setText(String.format("%.6f", VDL/VDY));

        YE.setText(String.format("%.6f", VDE/VDY));
        LE.setText(String.format("%.6f", VDE/VDL));
        LY.setText(String.format("%.6f", VDY/VDL));

    }
}