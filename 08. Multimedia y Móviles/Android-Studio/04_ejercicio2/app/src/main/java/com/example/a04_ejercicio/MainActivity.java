package com.example.a04_ejercicio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private enum Entrada{NINGUNA,DIGITO,OPERADOR,CE}

    private Entrada ultimaEntrada;
    private BigDecimal operando1;
    private BigDecimal operando2;

    private char operador;
    private byte numOperando;
    private Boolean puntoDecimal;
    TextView Pantallita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Pantallita = (TextView) findViewById(R.id.pantalla);
        operando1 = new BigDecimal(0);
        operando2 = new BigDecimal(0);
        numOperando = 0;
        operador = 0;
        puntoDecimal = false;
        ultimaEntrada = Entrada.NINGUNA;
    }

    /**************************************************************/
    public void pulsarDigito(View v){
        String t=((Button)v).getText().toString(); //El texto del boton

        // Si se pulsa el botón "vaciar", reiniciar todo
        if(t.equals("C")) {
            Iniciar(v);
            return;
        }

        String textoActual = Pantallita.getText().toString();

        // Si la última entrada no fue un dígito (ej: un operador, o "CE", o NINGUNA)
        if(ultimaEntrada != Entrada.DIGITO){
            // Empezamos un número nuevo.
            // Excepción: Si el texto actual ES "-" (de la operación negativa), SÍ queremos añadir el dígito.
            if (textoActual.equals("-")) {
                Pantallita.setText(textoActual + t);
            } else {
                // Si no, borramos la pantalla y ponemos el nuevo dígito.
                Pantallita.setText(t);
            }
            ultimaEntrada = Entrada.DIGITO;
            puntoDecimal = false;
        } else {
            // Estamos continuando un número.
            // Evitar "00" al principio.
            if (textoActual.equals("0") && t.equals("0")) {
                return;
            }
            // Si el texto es "0" y pulsamos otro dígito (ej: "7"), reemplazar el "0" por "7".
            if (textoActual.equals("0") && !t.equals("0") && !t.equals(".")) {
                Pantallita.setText(t);
            } else {
                // Si no, solo añadir.
                Pantallita.setText(textoActual + t);
            }
        }
    }
    /**************************************************************/

    public void Operacion(View v) {
        String t = ((Button)v).getText().toString(); // Texto del botón

        // FIX: Permitir escribir un número negativo al inicio O después de un operador
        if (t.equals("-")) {
            // Caso 1: Empezar con un número negativo (ej: -5)
            // Caso 2: Introducir un negativo después de un operador (ej: 5 + -3)
            if ((numOperando == 0 && ultimaEntrada != Entrada.DIGITO) || (ultimaEntrada == Entrada.OPERADOR)) {
                Pantallita.setText("-");
                ultimaEntrada = Entrada.DIGITO; // Preparamos para escribir el resto del número
                return; // Salimos para no procesar como una operación de resta
            }
        }

        // Solo contar nuevo operando si la última entrada fue un dígito
        if (ultimaEntrada == Entrada.DIGITO) {
            numOperando++;
        } else if (ultimaEntrada == Entrada.OPERADOR) {
            // Si el usuario pulsa otro operador seguido (y NO es el caso de negativo de arriba),
            // solo cambiamos el operador.
            operador = t.charAt(0);
            return;
        } else if (ultimaEntrada == Entrada.NINGUNA && numOperando == 1) {
            // Caso especial: El usuario pulsó "=" y ahora pulsa un operador.
            // operando1 ya tiene el resultado. Solo fijamos el nuevo operador.
            // (El "return" de abajo no aplica aquí)
        } else {
            // Si la última entrada fue NINGUNA (al inicio) o CE, y no es "-", no hacer nada.
            return;
        }


        // Si es el primer operando
        if (numOperando == 1) {
            try {
                operando1 = new BigDecimal(Pantallita.getText().toString());
            } catch (NumberFormatException e) {
                operando1 = BigDecimal.ZERO;
            }
        } else if (numOperando == 2) {
            try {
                operando2 = new BigDecimal(Pantallita.getText().toString());
            } catch (NumberFormatException e) {
                operando2 = BigDecimal.ZERO;
            }

            BigDecimal resultado = realizarOperacion(operando1, operando2, operador);
            if (resultado != null) {
                Pantallita.setText(resultado.toString());
                operando1 = resultado;
            }
            numOperando = 1;
        }

        operador = t.charAt(0);
        ultimaEntrada = Entrada.OPERADOR;
        puntoDecimal = false;
    }

    /**************************************************************/
    private BigDecimal realizarOperacion(BigDecimal op1, BigDecimal op2, char operador) {
        try {
            switch (operador){
                case '+':
                    return op1.add(op2);
                case '-':
                    return op1.subtract(op2);
                case 'x':
                    return op1.multiply(op2);
                case '÷':
                    // Evitar división por cero
                    if(op2.compareTo(BigDecimal.ZERO) == 0) {
                        Pantallita.setText("Error");
                        return null;
                    }
                    return op1.divide(op2, 10, RoundingMode.HALF_UP).stripTrailingZeros();
                default:
                    return op2;
            }
        } catch (Exception e) {
            Pantallita.setText("Error");
            return null;
        }
    }
    /**************************************************************/

    public void calcularResultado(View v) {
        // Solo calcular si la última entrada fue un dígito Y tenemos un operando1
        if (ultimaEntrada == Entrada.DIGITO && numOperando >= 1) {
            try {
                operando2 = new BigDecimal(Pantallita.getText().toString());
            } catch (NumberFormatException e) {
                operando2 = BigDecimal.ZERO;
            }

            BigDecimal resultado = realizarOperacion(operando1, operando2, operador);
            if(resultado != null) {
                Pantallita.setText(resultado.toString());
                operando1 = resultado; // Guardar resultado como operando1 para seguir operando
                numOperando = 1;       // Listo para la siguiente operación
                ultimaEntrada = Entrada.NINGUNA; // FIX: Indica que se mostró un resultado, no un dígito
            }
        }
    }

    /**************************************************************/

    public void tantoPorCiento(View v) {
        BigDecimal resultado;
        if (ultimaEntrada == Entrada.DIGITO) {
            try {
                resultado = BigDecimal.valueOf(Double.valueOf((Pantallita.getText().toString()))).divide(new BigDecimal(100));
                resultado = operando1.multiply(resultado);
                Pantallita.setText(resultado.toString());
                operando1 = resultado;
                numOperando = 1;
                ultimaEntrada = Entrada.NINGUNA;
                puntoDecimal = false;
            } catch (Exception e) {
                Pantallita.setText("Error");
            }
        }
    }

    /**************************************************************/

    public void Iniciar(View v){
        Pantallita.setText("0.");
        operando1 = BigDecimal.ZERO;
        operando2 = BigDecimal.ZERO;
        numOperando = 0;
        operador = 0;
        puntoDecimal = false;
        ultimaEntrada = Entrada.NINGUNA;
    }

    /**************************************************************/

    public void BorrarEntrada(View v){
        Pantallita.setText("0.");
        puntoDecimal = false;
        ultimaEntrada = Entrada.CE;
    }

    /**************************************************************/

    public void PuntoDec(View v){
        if(ultimaEntrada!= Entrada.DIGITO){
            Pantallita.setText("0.");
            ultimaEntrada = Entrada.DIGITO;
        }

        String textoActual = Pantallita.getText().toString();
        if(!textoActual.contains(".")){
            Pantallita.setText(textoActual + ".");
            puntoDecimal = true;
        }
    }
    /**************************************************************/
}