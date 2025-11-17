package com.example.a06_ejercicio;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView suma;
    TextView resta;
    TextView multiplicacion;
    TextView division;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        suma = findViewById(R.id.tvSuma);
        resta = findViewById(R.id.tvResta);
        multiplicacion = findViewById(R.id.tvMultiplicacion);
        division = findViewById(R.id.tvDivision);

        Bundle b = getIntent().getExtras();
        assert b != null;
        double num1 = b.getDouble("num1");
        double num2 = b.getDouble("num2");
        suma.setText(String.format("%.2f", num1 + num2));
        resta.setText(String.format("%.2f", num1 - num2));
        multiplicacion.setText(String.format("%.2f", num1 * num2));
        if (num2 != 0) {
            division.setText(String.format("%.2f", num1 / num2));
        } else {
            Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
        }
    }
    public void salir(View v){
        finish();
    }
}