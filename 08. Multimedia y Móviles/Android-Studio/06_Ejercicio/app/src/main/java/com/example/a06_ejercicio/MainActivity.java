package com.example.a06_ejercicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

EditText et1;
EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.num1);
        et2 = findViewById(R.id.num2);
    }

    public void enviar(View v){
        double num1 =Double.parseDouble(et1.getText().toString());
        double num2 =Double.parseDouble(et2.getText().toString());
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("num1",num1);
        i.putExtra("num2",num2);
        startActivity(i);
    }
}