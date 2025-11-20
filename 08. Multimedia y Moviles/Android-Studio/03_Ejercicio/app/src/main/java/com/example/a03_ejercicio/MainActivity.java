package com.example.a03_ejercicio;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText jtgc, jtgf, jtgk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        jtgc = findViewById(R.id.centigrados);
        jtgf = findViewById(R.id.fahrenheit);
        jtgk = findViewById(R.id.kelvin);

        jtgc.setOnEditorActionListener((v, actionId, event) -> {
            general(jtgc, 0);
            return false;
        });

        jtgf.setOnEditorActionListener((v, actionId, event) -> {
            general(jtgf, 0);
            return false;
        });

        jtgk.setOnEditorActionListener((v, actionId, event) -> {
            general(jtgk, 0);
            return false;
        });
    }

    public void decrementarCentigrados(View vista) { general(jtgc, -1); }
    public void incrementarCentigrados(View vista) { general(jtgc, 1); }
    public void decrementarFahrenheit(View vista) { general(jtgf, -1); }
    public void incrementarFahrenheit(View vista) { general(jtgf, 1); }
    public void decrementarKelvin(View vista) { general(jtgk, -1); }
    public void incrementarKelvin(View vista) { general(jtgk, 1); }

    public void general(EditText t, int a) {
        double n = Double.parseDouble(t.getText().toString()) + a;

        t.setText(String.valueOf(n));

        if (t == jtgc) {
            // Centígrados → Fahrenheit y Kelvin
            jtgf.setText(String.valueOf((n * 9 / 5) + 32));
            jtgk.setText(String.valueOf(n + 273.15));

        } else if (t == jtgf) {
            // Fahrenheit → Centígrados y Kelvin
            double c = (n - 32) * 5 / 9;
            jtgc.setText(String.valueOf(c));
            jtgk.setText(String.valueOf(c + 273.15));

        } else if (t == jtgk) {
            // Kelvin → Centígrados y Fahrenheit
            double c = n - 273.15;
            jtgc.setText(String.valueOf(c));
            jtgf.setText(String.valueOf((c * 9 / 5) + 32));
        }
    }
}
