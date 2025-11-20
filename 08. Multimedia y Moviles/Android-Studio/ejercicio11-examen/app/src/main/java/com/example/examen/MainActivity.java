package com.example.examen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listViewOperaciones;
    private TextView  inputResultado;
    private EditText inputUno, inputdos;

    String [] operaciones = {"Sumar", "Restar", "Multiplicar", "Dividir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listViewOperaciones=findViewById(R.id.operaciones);
        inputUno=findViewById(R.id.inputuno);
        inputdos=findViewById(R.id.inputdos);
        inputResultado=findViewById(R.id.resultados);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, operaciones);
        listViewOperaciones.setAdapter(adapter);

        listViewOperaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valorUno = inputUno.getText().toString();
                String valorDos = inputdos.getText().toString();

                if (!valorUno.isEmpty() && !valorDos.isEmpty()) {
                    double numUno = Double.parseDouble(valorUno);
                    double numDos = Double.parseDouble(valorDos);

                    double resultado = 0;
                    switch (position) {
                        case 0:
                            resultado = numUno + numDos;
                            break;
                        case 1:
                            resultado = numUno - numDos;
                            break;
                        case 2:
                            resultado = numUno * numDos;
                            break;
                        case 3:
                            if (numDos != 0) {
                                resultado = numUno / numDos;
                            } else {
                                inputResultado.setText("Error: División por cero");
                                return;
                            }
                            break;
                    }

                    inputResultado.setText("Resultado: " + resultado);
                } else {
                    inputResultado.setText("Error: Ingresa ambos números");
                }
            }
        });
    }
}