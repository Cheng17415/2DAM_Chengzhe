package com.example.ejemploclaseej05;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView bienvenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        bienvenido = findViewById(R.id.saludo);

        Bundle b = getIntent().getExtras();
        String dato = b.getString("usuario");
        bienvenido.setText("Bienvenido " + dato);
    }
    public void salir(View v){
        finish();
    }
}