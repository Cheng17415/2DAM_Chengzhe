package com.example.memorias_examen;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Asegúrate de que estas imágenes existan en res/drawable
    private final int[] imgs = {
            R.drawable.christmas_tree, R.drawable.santa_1, R.drawable.santa_2,
            R.drawable.santa_3, R.drawable.santa_4, R.drawable.santa_5,
            R.drawable.santa_6, R.drawable.santa_7, R.drawable.santa_8
    };

    // Fondo de la carta (cuando está boca abajo)
    private final int fondo = R.drawable.ic_launcher_background; // Ojo: Cambia esto por tu imagen de reverso si tienes una

    private ImageView[] casillas = new ImageView[16];
    private int[] posiciones = new int[16];
    private int numero1 = 0, numero2 = 0, vnumero1 = 0, vnumero2 = 0, contador = 0;
    private boolean turno;
    private boolean bloqueo = false; // Para evitar clicks rápidos mientras se voltean

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turno = true; // true = primera carta, false = segunda carta

        // CORRECCIÓN 1: Usar -1 para vacío, porque 0 es una imagen válida
        Arrays.fill(posiciones, -1);
        aleatorio();

        TableLayout reji = findViewById(R.id.tb_memoria);
        int k = 0;

        for (int i = 0; i < reji.getChildCount(); i++) {
            View child = reji.getChildAt(i);

            // CORRECCIÓN 2: Verificar que el hijo es una fila (TableRow) para evitar el crash con el TextView
            if (child instanceof TableRow) {
                TableRow fila = (TableRow) child;
                for (int j = 0; j < fila.getChildCount(); j++) {
                    casillas[k] = (ImageView) fila.getChildAt(j);

                    // Asignar imagen de fondo inicial
                    // casillas[k].setImageResource(R.drawable.tu_imagen_reverso);

                    casillas[k].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!bloqueo) {
                                elegido(v.getId());
                            }
                        }
                    });
                    k++;
                }
            }
        }
    }

    private int alea(int li, int ls) {
        return (int) (Math.random() * (ls - li + 1)) + li;
    }

    private void elegido(int n) {
        // CORRECCIÓN 3: Asegurar que obtenemos el ID correctamente y gestionamos el índice
        View v = findViewById(n);
        String cadena = getResources().getResourceEntryName(n);

        // Extraer el número del ID (ej: C_1 -> 1) y restar 1 para el array (0-15)
        int m = Integer.valueOf(cadena.substring(cadena.indexOf("_") + 1)) - 1;

        // Si la casilla ya fue adivinada (marcada como null o -2) o es la misma que acabamos de tocar, salimos
        if (posiciones[m] == -2 || (!turno && vnumero1 == n)) return;

        // Mostrar la imagen correspondiente
        ((ImageView) v).setImageResource(imgs[posiciones[m]]);

        if (turno) {
            // Primer turno
            numero1 = posiciones[m]; // Guardamos el ID de la imagen (0-7)
            vnumero1 = n;            // Guardamos el ID de la View (R.id.C_X)
            turno = false;
        } else {
            // Segundo turno
            numero2 = posiciones[m];
            vnumero2 = n;            // CORRECCIÓN 4: Guardar vnumero2 aquí

            if (numero1 == numero2) {
                // Acierto
                // Marcamos las posiciones como resueltas (usamos -2 para diferenciar de vacío -1)
                int m1 = Integer.valueOf(getResources().getResourceEntryName(vnumero1).split("_")[1]) - 1;
                posiciones[m] = -2;
                posiciones[m1] = -2;

                ((TextView) findViewById(R.id.contador)).setText(String.valueOf(++contador));
                turno = true; // Reseteamos turno para el siguiente par
            } else {
                // Fallo
                bloqueo = true; // Bloqueamos clicks hasta que se volteen
                cambiar();
                turno = true;
            }
        }
    }

    private void cambiar() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Volvemos a poner la imagen de "reverso" o "christmas_tree" como tenías
                // Ojo: Si christmas_tree es parte del juego, deberías tener una imagen aparte para el reverso.
                // Aquí asumo que quieres ocultarlas de nuevo:
                ((ImageView) findViewById(vnumero2)).setImageResource(imgs[0]); // O pon tu R.drawable.reverso
                ((ImageView) findViewById(vnumero1)).setImageResource(imgs[0]); // O pon tu R.drawable.reverso
                bloqueo = false;
            }
        }, 1000); // 1 segundo de espera
    }

    private void aleatorio() {
        int n;
        // CORRECCIÓN 5: Bucle solo hasta 7 (8 imágenes), no hasta 8.
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 2; j++) {
                while (true) {
                    n = alea(0, 15);
                    // Buscamos hueco que sea -1
                    if (posiciones[n] == -1) {
                        posiciones[n] = i;
                        break;
                    }
                }
            }
        }
    }
}