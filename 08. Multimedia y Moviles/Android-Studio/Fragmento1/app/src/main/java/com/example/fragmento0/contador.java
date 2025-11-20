package com.example.fragmento0;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class contador extends Fragment {

    private Button boton;
    private EditText edit;
    private TextView text;
    private static int variable_top=20;

    public contador() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_contador, container, false);

        /**************************************************************/

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        params.addRule(RelativeLayout.BELOW,R.id.boton);
        params.setMargins(0,variable_top,0,0);
        vista.setLayoutParams(params);
        variable_top += 300;
        /**************************************************************/

        boton = vista.findViewById(R.id.procesar);
        edit = vista.findViewById(R.id.frase);
        text = vista.findViewById(R.id.contador);

        boton.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              String mensaje = edit.getText().toString();
              text.setText(String.valueOf(mensaje.length()));

          }
        });
        return vista;


    }
}