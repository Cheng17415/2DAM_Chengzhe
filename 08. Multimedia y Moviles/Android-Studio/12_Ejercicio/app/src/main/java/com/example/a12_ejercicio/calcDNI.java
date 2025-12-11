package com.example.a12_ejercicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calcDNI extends Fragment {
    private static final String TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";

    public calcDNI() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc_d_n_i, container, false);

        EditText numDNI = view.findViewById(R.id.numDNI);
        Button calcBtn = view.findViewById(R.id.calcLetra);
        TextView resultado = view.findViewById(R.id.resultado);

        calcBtn.setOnClickListener(v -> {
            String input = numDNI.getText().toString().toUpperCase().trim();

            if (input.isEmpty()) {
                resultado.setText("Introduce un número");
                return;
            }

            int numero;

            // NIE
            if (input.startsWith("X") || input.startsWith("Y") || input.startsWith("Z")) {
                char prefijo = input.charAt(0);
                String numRest = input.substring(1);

                if (!numRest.matches("\\d+")) {
                    resultado.setText("Formato incorrecto");
                    return;
                }

                switch (prefijo) {
                    case 'X': numero = Integer.parseInt("0" + numRest); break;
                    case 'Y': numero = Integer.parseInt("1" + numRest); break;
                    case 'Z': numero = Integer.parseInt("2" + numRest); break;
                    default: numero = 0;
                }

            } else {
                // DNI
                if (!input.matches("\\d+")) {
                    resultado.setText("Formato incorrecto");
                    return;
                }
                numero = Integer.parseInt(input);
            }

            int resto = numero % 23;
            char letra = TABLA.charAt(resto);

            resultado.setText("Letra: " + letra);
        });

        return view;
    }
}