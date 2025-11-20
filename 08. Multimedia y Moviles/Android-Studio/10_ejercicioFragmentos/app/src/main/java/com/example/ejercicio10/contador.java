package com.example.ejercicio10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class contador extends Fragment {

    private Button boton;
    private EditText edit;
    private TextView text;

    public contador() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_contador, container, false);
        boton = vista.findViewById(R.id.procesar);
        edit = vista.findViewById(R.id.frase);
        text = vista.findViewById(R.id.contador);

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mensaje = edit.getText().toString();
                text.setText(String.valueOf(mensaje.length()));
            }

        });
        return vista;
    }
}