package com.example.simuladorspinner;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner lv;
    EditText et1, et2;
    TextView t1, res;
    String[] operaciones = {"+", "-", "*", "/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.num1);
        et2 = findViewById(R.id.num2);
        t1 = findViewById(R.id.operacion);
        res = findViewById(R.id.resultado);
        lv = findViewById(R.id.operaciones);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, operaciones);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lv.setAdapter(adaptador);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            t1.setText(lv.getItemAtPosition(position).toString());
            res.setText(calcularResultados(et1, et2, t1.getText().toString()));

        });
        escuchador(et1);
        escuchador(et2);
    }

    private void escuchador(EditText et){
        et.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                res.setText(calcularResultados(et1,et2,t1.getText().toString()));
            }
        });
        et.setOnEditorActionListener((v, actionId, event) -> {
            res.setText(calcularResultados(et1,et2,t1.getText().toString()));
            return true;
        });
    }
    private String calcularResultados(EditText v1, EditText v2, String operacion){
        String textoNum1 = v1.getText().toString();
        String textoNum2 = v2.getText().toString();

        if (textoNum1.isEmpty() || textoNum2.isEmpty() || operacion.isEmpty()) {
            return ""; // No hacer nada si falta algún dato
        }
        try{
            double num1 = Double.parseDouble(v1.getText().toString());
            double num2 = Double.parseDouble(v2.getText().toString());
            switch(operacion){
                case "+":
                    return String.valueOf(redondear(num1 + num2,2));
                case "-":
                    return String.valueOf(redondear(num1 - num2,2));
                case "*":
                    return String.valueOf(redondear(num1 * num2,2));
                case "/":
                    if(num2 == 0) return "Error";
                    return String.valueOf(redondear(num1 / num2,2));
                default:
                    return "Error";
            }
        } catch (NumberFormatException e) {
            // Si por alguna razón el texto no es un número, no hacer nada
            return "";
        }

    }

    public double redondear(double num, int decimales){
        double factor = Math.pow(10, decimales);
        return Math.round(num * factor) / factor;
    }
}