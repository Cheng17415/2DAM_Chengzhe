package principal;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Encuesta {
	private static final int NUM_ZONAS = 20;
	
	/*Simular encuestas en 20 zonas. Cada resultado esta comprendido entre 1 y 9 o No contesta
	 * Visualizar el numero de respuestas por zona y cuantas respuestas tienen el mismo numero*/
	public static void main(String[] args) {
		ResultadosEncuesta resultados = new ResultadosEncuesta();
		Thread[] encuestadores = new Thread[NUM_ZONAS];
		for (int i = 0; i < NUM_ZONAS; i++) {
			encuestadores[i] = new Thread(new EncuestadorZona("zona_"+(i+1),resultados));
		}
		for (Thread encuestador : encuestadores) {encuestador.start();}
		
		for (Thread encuestador : encuestadores) {
			try {
				encuestador.join();
			}catch(InterruptedException ex) {}
		}
		
		/****************************************************/
		System.out.println("Encuestas por Zonas");
		int total = 0, parcial;
		for (String zona : resultados.obtenZonas()) {
			parcial = resultados.obtenNumRespuestasZona(zona);
			total += parcial;
			System.out.printf("%s: %d\n",zona,parcial);
		}
		System.out.printf("TOTAL: %d\n", total);
		
		/****************************************************/
		System.out.println("Encuestas por Respuestas");
		total = 0;
		for (String respuesta : resultados.obtenRespuestas()) {
			parcial = resultados.obtenNumRespuestas(respuesta);
			total += parcial;
			System.out.printf("%s: %d\n",respuesta !=null ? respuesta: "NS/NC",parcial);
		}
		System.out.printf("TOTAL: %d\n", total);
	}
}

/**********************************************************************************************/
class ResultadosEncuesta{
	private final HashMap<String, Integer> totalPorRespuesta = new HashMap <String, Integer>();
	private final HashMap<String, Integer> totalPorZona = new HashMap <String, Integer>();
	
	synchronized public void anotaRespuesta(String idZona, String repuesta) {
		Integer n = this.totalPorRespuesta.get(repuesta);
		this.totalPorRespuesta.put(repuesta, n == null ? 1: n+1);
		n = this.totalPorZona.get(idZona);
		this.totalPorZona.put(idZona, n == null ? 1 : n+1);
	}
	
	synchronized public Set<String> obtenZonas(){
		return this.totalPorZona.keySet();
	}
	
	synchronized public Set<String> obtenRespuestas(){
		return this.totalPorRespuesta.keySet();
	}
	
	synchronized public int obtenNumRespuestasZona(String zona){
		return this.totalPorZona.get(zona);
	}
	
	synchronized public int obtenNumRespuestas(String respuesta){
		return this.totalPorRespuesta.get(respuesta);
	}
}
/************************************************************************************/
class aleatorio{
	public static int alea(int li,int ls) {
		return (int)(Math.random()*(ls-li +1)) +li;
	}
}
/************************************************************************************/
class EncuestadorZona implements Runnable{
	
	public final String idZona;
	private final ResultadosEncuesta resultados;
	
	public EncuestadorZona(String idZona, ResultadosEncuesta resultados) {
		super();
		this.idZona = idZona;
		this.resultados = resultados;
	}

	@Override
	public void run() {
		String respuesta;
		System.out.printf(">>Encuestador para %s comienza.\n", this.idZona);
		int numRespuestas = aleatorio.alea(100, 200);
		for (int i = 0; i < numRespuestas; i++) {
			int puntuacion = aleatorio.alea(0, 9);
			respuesta = puntuacion == 0 ? null : "Respuesta_" + puntuacion;
			this.resultados.anotaRespuesta(this.idZona, respuesta);
		}
		System.out.printf("Encuestador zona %s terminada. \n", this.idZona);
	}
	
}
