package com.example.simuladorspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner lv;
    EditText et1, et2;
    TextView res;

    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.num1);
        et2 = findViewById(R.id.num2);
        res = findViewById(R.id.resultado);
        lv = findViewById(R.id.operaciones);

        String[] operaciones = getResources().getStringArray(R.array.matriz);
        op = "suma";

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operaciones);
        lv.setAdapter(adaptador);
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                op = operaciones[position];
                cambio();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        escuchador(et1);
        escuchador(et2);
    }

    private void escuchador(EditText et){
        et.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                cambio();
            }
        });
        et.setOnEditorActionListener((v, actionId, event) -> {
            cambio();
            return true;
        });
    }
    private void cambio(){
        String textoNum1 = et1.getText().toString();
        String textoNum2 = et2.getText().toString();
        double rs;
        if (textoNum1.isEmpty() || textoNum2.isEmpty()) {
            return;
        }
        try{
            double num1 = Double.parseDouble(textoNum1);
            double num2 = Double.parseDouble(textoNum2);

            rs = op.equals("+") ? num1 + num2 : op.equals("-") ? num1 - num2 : op.equals("*") ? num1 * num2 : op.equals("/") ? num1/ num2 : 0.0;
            res.setText(String.valueOf(redondear(rs, 2)));
        } catch (NumberFormatException e) {
            res.setText("Error");
        }

    }

    public double redondear(double num, int decimales){
        double factor = Math.pow(10, decimales);
        return Math.round(num * factor) / factor;
    }
}