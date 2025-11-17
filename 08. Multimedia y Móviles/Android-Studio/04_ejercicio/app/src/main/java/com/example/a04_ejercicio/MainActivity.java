package com.example.a04_ejercicio;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 1. Declarar variables para todos los campos EditText
    private EditText inputTipoCambio;
    private EditText inputValorDolar;
    private EditText inputValorEuro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 2. Conectar las variables Java con los IDs de XML
        inputTipoCambio = findViewById(R.id.inputValorDollarCurrency);
        inputValorDolar = findViewById(R.id.inputValorDollar);
        inputValorEuro = findViewById(R.id.inputValorEuro);

        // 3. Establecer valores iniciales
        inputTipoCambio.setText("0.92");
        inputValorDolar.setText("1.00");

        actualizarConversion();
    }
    // MANEJO DE CLICS DOLAR
    /*View es la clase base de todos los componentes de la interfaz de usuario que se
    pueden dibujar en la pantalla de Android,*/
    public void modificarDolar(View vista) {
        String textoDolar = inputValorDolar.getText().toString();
        double valorDolar = 0.0;

        try {
            valorDolar = Double.parseDouble(textoDolar);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor no válido. Reiniciando a 0.", Toast.LENGTH_SHORT).show();
            valorDolar = 0.0;
        }

        int idBoton = vista.getId();

        if (idBoton == R.id.btnMasDolar) {
            valorDolar += 1;
        } else if (idBoton == R.id.btnMenosDolar) {
            if (valorDolar >= 1) {
                valorDolar -= 1;
            } else {
                valorDolar = 0;
            }
        }

        inputValorDolar.setText(String.format("%.2f", valorDolar));

        actualizarConversion();
    }

    // MANEJO DE CLICS EURO
    public void modificarEuro(View vista) {
        String textoEuro = inputValorEuro.getText().toString();
        double valorEuro = 0.0;

        try {
            valorEuro = Double.parseDouble(textoEuro);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor no válido. Reiniciando a 0.", Toast.LENGTH_SHORT).show();
            valorEuro = 0.0;
        }
        int idBoton = vista.getId();

        if (idBoton == R.id.btnMasEuro) {
            valorEuro += 1;
        } else if (idBoton == R.id.btnMenosEuro) {
            if (valorEuro >= 1) {
                valorEuro -= 1;
            } else {
                valorEuro = 0;
            }
        }

        inputValorEuro.setText(String.format("%.2f", valorEuro));

        actualizarConversionInversa();
    }

    // CONVERSIÓN
    private double obtenerTipoDeCambio() {
        String textoTipoCambio = inputTipoCambio.getText().toString();
        try {
            return Double.parseDouble(textoTipoCambio);
        } catch (NumberFormatException e) {
            // Si el campo está vacío o no es un número, usamos un valor por defecto
            Toast.makeText(this, "Error en tipo de cambio, usando 0.92", Toast.LENGTH_SHORT).show();
            return 0.92;
        }
    }

    private void actualizarConversion() {
        // Convierte Dólar a Euro (cuando se pulsa un botón de Dólar)
        double tipoCambio = obtenerTipoDeCambio();

        String textoDolar = inputValorDolar.getText().toString();
        try {
            double valorDolar = Double.parseDouble(textoDolar);
            double valorEuro = valorDolar * tipoCambio;

            inputValorEuro.setText(String.format("%.2f", valorEuro));

        } catch (NumberFormatException e) {
            inputValorEuro.setText("0.00");
        }
    }

    private void actualizarConversionInversa() {
        // Convierte Euro a Dólar (cuando se pulsa un botón de Euro)
        double tipoCambio = obtenerTipoDeCambio();

        String textoEuro = inputValorEuro.getText().toString();
        try {
            double valorEuro = Double.parseDouble(textoEuro);

            double valorDolar = valorEuro / tipoCambio;

            inputValorDolar.setText(String.format("%.2f", valorDolar));

        } catch (NumberFormatException e) {
            inputValorDolar.setText("0.00");
        }
    }
}