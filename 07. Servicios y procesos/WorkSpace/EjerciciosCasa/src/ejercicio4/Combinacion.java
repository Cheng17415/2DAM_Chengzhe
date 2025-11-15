package ejercicio4;

import java.util.Arrays;
import java.util.HashMap;

public class Combinacion {
    final int NumColoresCombinacion = 4;
    /* Número de colores de una combinación */
    final int NumColoresJuego = 6;
    /* Número total de posibles colores del juego */

    /* Tabla de NumColoresCombinacion elementos que representa la combinación */
    char[] tablaColores = {'R', 'A', 'V', 'Z', 'M', 'N'};/* Posibles colores dentro del juego Rojo Amarillo Verde Azul Morado Negro*/

    char[] combSecreto = new char[NumColoresCombinacion];

    public Combinacion() {
        this.combSecreto = generarCombiSecreta();
    }

    public Combinacion(char[] combSecreta) {
        this.combSecreto = combSecreta;
    }

    public char[] getCombSecreto() {
        return combSecreto;
    }

    public void setCombSecreto(char[] combSecreto) {
        this.combSecreto = combSecreto;
    }

    public int getNumColoresCombinacion() {
        return NumColoresCombinacion;
    }

    public int getNumColoresJuego() {
        return NumColoresJuego;
    }

	public char[] generarCombiSecreta(){
        for (int i = 0; i <combSecreto.length; i++) {
            int numAlea = alea(0,NumColoresJuego - 1);
            combSecreto[i] = tablaColores[numAlea];
        }
        return combSecreto;
    }
    
    public char[] EvaluacionCombinacion(char[] combi, char[] resultado) {
        HashMap<Character, Integer> hm = new HashMap<>();
        Arrays.fill(resultado, '_');
        //Con este método, obtengo en la array resultado los colores que estén en la posición correcta con una 'R'(rojo)
       
        resultado = obtenerCorrectas(resultado,combi);
        
        for (int i = 0; i < combi.length; i++) {
            if(resultado[i]== 'R') break;
            /*En el HashMap añado la letra que aparece en el intento como key, 
            y el value son las ocurrencias que aparece en la combinacion secreta (que no sean los colores en la posición correcta)*/
            hm.put(combi[i], aparicion(combi[i],combSecreto));
            
            //La primera aparición de un color que esté en una posición equivocada se marca con el color blanco('B')  
            if(hm.get(combi[i]) > 0){
                resultado[i] = 'B';
                hm.replace(combi[i], hm.get(combi[i]--));
            }
        }

        return resultado;
    }

    private int aparicion(char c, char[] letras) {
        int apa = 0;
        for (char letra : letras) {
            if (c == letra) {
                apa++;
            }
        }
        return apa;
    }
    
    private char[] obtenerCorrectas(char[] resultado, char[] combi){
        for (int i = 0; i < combi.length; i++) {
            for (int j = 0; j < combSecreto.length; j++) {
                if (combi[i] == combSecreto[j] && i == j) {
                    resultado[i] = 'R';
                    //Tras saber que un color está en la posición correcta, la sustituyo por un caracter cualquiera 
                    //que no sea un color disponible para facilitar el proceso.
                    combSecreto[j] = '0';
                    break;
                }
            }
        }
        return resultado;
    }
    
     public int alea(int li, int ls) {
        return (int) (Math.random() * (ls - li + 1)) + li;
    }
}
