package com.example.examen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final int[] imgs = {R.drawable.sun,R.drawable.moon};
    private final int fondo =R.drawable.cloudy;
    private ImageView[] casillas = new ImageView[16];
    private int[][] posiciones = new int[4][4];
    private TextView tvTurno;
    private TextView tvIniciar;
    private int turno = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvTurno = findViewById(R.id.turno);
        tvIniciar = findViewById(R.id.juegoIni);
        tvIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarJuego();
            }
        });
    }
    private void elegido(int n){
        View v =findViewById(n);
        String cadena = getResources().getResourceEntryName(n);
        // Extraer el número del ID (C_1 -> 1) y restar 1 para el array (0-15)
        int m = Integer.valueOf(cadena.substring(cadena.indexOf("_") + 1)) - 1;
        //Si ya ha sido clickado antes, volver
        int columna = m % 4;
        int fila = m / 4;
        if(posiciones[fila][columna] != -1) return;
        ((ImageView) v).setImageResource(imgs[turno -1]);
        tvTurno.setText("Turno de Jugador: " + turno);
        //Meto el turno del jugador que le ha dado click en la array.
        posiciones[fila][columna] = turno;
        comprobarGanador(fila,columna);
        turno = (turno == 1) ? 2 : 1;
    }
    public void comprobarGanador(int fila, int columna){
        int cuantosf = 0, cuantosc = 0, cuantosd1 = 0, cuantosd2 = 0;
        for(int i = 0; i<4;i++){
            if(posiciones[i][columna] == turno){
                cuantosf ++;
            }
            if(posiciones[fila][i] == turno){
                cuantosc ++;
            }
        }
        for (int i = 0; i < 4; i++) {
            if(posiciones[i][i] == turno){
                cuantosd1 ++;
            }
            if(posiciones[3-i][i] == turno){
                cuantosd2 ++;
            }
        }
        if(cuantosc == 4 || cuantosf == 4 || cuantosd1 == 4|| cuantosd2 == 4){
            Toast.makeText(this,"El jugador "  +turno+" ha ganado",Toast.LENGTH_LONG).show();
            resetear();
        }
    }
    private void resetear(){
        posiciones = new int[4][4];
        turno = 1;
        tvIniciar.setText(R.string.iniciar);
        tvTurno.setText("Turno de Jugador");
        for (ImageView imagen:casillas) {
            imagen.setImageResource(fondo);
        }
    }
    private void iniciarJuego(){

        tvIniciar.setText(R.string.iniciado);
        tvTurno.setText("Turno de Jugador: " + turno);
        for (int[] pos: posiciones) {
            Arrays.fill(pos,-1);
        }
        TableLayout table  = findViewById(R.id.tb_raya);
        int k = 0;
        for (int i = 0; i < table.getChildCount(); i++) {
            View child = table.getChildAt(i);
            if (child instanceof TableRow) {
                TableRow fila = (TableRow) child;
                for (int j = 0; j < fila.getChildCount(); j++) {
                    casillas[k] = (ImageView) fila.getChildAt(j);
                    casillas[k].setOnClickListener(v -> {
                        elegido(v.getId());
                    });
                    k++;
                }
            }
        }
    }

}