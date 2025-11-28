package ejercicio4;
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
    
	public char[] EvaluacionCombinacion(char[] combi) {
	    char[] resultado = new char[NumColoresCombinacion];

	    // Copias para no modificar los originales
	    char[] secretoTemp = combSecreto.clone();
	    char[] intentoTemp = combi.clone();

	    int r = 0; // rojos
	    int b = 0; // blancos

	    // ===== 1ª PASADA: ROJOS =====
	    for (int i = 0; i < NumColoresCombinacion; i++) {
	        if (intentoTemp[i] == secretoTemp[i]) {
	            r++;
	            // Marcamos como usados
	            secretoTemp[i] = '-';
	            intentoTemp[i] = '*';
	        }
	    }

	    // ===== 2ª PASADA: BLANCOS =====
	    for (int i = 0; i < NumColoresCombinacion; i++) {
	        if (intentoTemp[i] == '*') continue; // ya contado como rojo

	        for (int j = 0; j < NumColoresCombinacion; j++) {
	            if (intentoTemp[i] == secretoTemp[j]) {
	                b++;
	                secretoTemp[j] = '-'; // marcamos como usado
	                break;
	            }
	        }
	    }

	    // Llenamos el resultado
	    int pos = 0;
	    for (int i = 0; i < r; i++) resultado[pos++] = 'R';
	    for (int i = 0; i < b; i++) resultado[pos++] = 'B';
	    while (pos < resultado.length) resultado[pos++] = '_';

	    return resultado;
	}
    
     public int alea(int li, int ls) {
        return (int) (Math.random() * (ls - li + 1)) + li;
    }
}
