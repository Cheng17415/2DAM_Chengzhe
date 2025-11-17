package com.example.conversionmonedas;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String [] nombres = {"dolar","euro","yen","libra"};
    HashMap<String, Double> valores = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        valores.put("dolar", 1.0);
        valores.put("euro", 0.719);
        valores.put("yen", 101.615);
        valores.put("libra", 0.596);

        for (int i = 1; i < nombres.length; i++){
            //dolareuro, dolaryen, dolarlibra
            String nombreID = nombres[0] + nombres[i];

            //Obtener el int de la id de esta forma ya que no funcionaba con R.id.
            int resID = getResources().getIdentifier(nombreID, "id", getPackageName());
            EditText currentEditText = findViewById(resID);

            //Cuando pierde el foco
            currentEditText.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) general((EditText) v, nombreID);
            });

            //Al dar a enter
            currentEditText.setOnEditorActionListener((v, actionId, event) -> {
                general((EditText) v, nombreID);
                return false;
            });
        }
    }

    private void general(EditText et, String id) {
        if (et.getText().toString().isEmpty()) return;
        double n = Double.parseDouble(et.getText().toString());

        // Actualiza el valor base del Dólar
        String destino = id.substring(nombres[0].length());
        valores.put(destino, n);

        // Bucle para recalcular todas las celdas TextView
        for(int i = 1; i < nombres.length; i++){ // Filas (euro, yen, libra)
            for(String nombre2 : nombres){

                String nombreID = nombres[i] + nombre2;

                if(nombres[i].equals(nombre2)) continue;

                Double val1 = valores.get(nombres[i]);
                Double val2 = valores.get(nombre2);

                int resID = getResources().getIdentifier(nombreID, "id", getPackageName());
                TextView v = findViewById(resID);

                v.setText(String.valueOf(redondear(val2 / val1, 6)));
            }
        }
    }

    private double redondear(double valor, int decimales){
        double factor = Math.pow(10, decimales);
        valor = valor * factor;
        long tmp = Math.round(valor);
        return (double) tmp / factor;
    }
}