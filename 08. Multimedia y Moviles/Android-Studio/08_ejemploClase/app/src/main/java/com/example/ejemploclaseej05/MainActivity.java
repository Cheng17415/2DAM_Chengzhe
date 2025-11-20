package com.example.ejemploclaseej05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.contrasena);
        usuario = findViewById(R.id.usuario);
    }
    public void verificar(View v){
        String cadena = et1.getText().toString();
        String usu = usuario.getText().toString();
        if(cadena.equals("abc123") && usu.equalsIgnoreCase("Jose")){
            Intent i = new Intent(this, MainActivity2.class);
            i.putExtra("usuario",usu);
            startActivity(i);
        }else{
            Toast notificacion = Toast.makeText(this,"Clave incorrecta",Toast.LENGTH_LONG);
            notificacion.show();
        }
    }
}