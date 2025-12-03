package ejFilosofos;

public class Principal {
	public static void main(String[] args) {
		final int CNTFILOSOFOS = 5;
		Filosofo [] filosofos = new Filosofo[CNTFILOSOFOS];
		Object[] palillos = new Object[CNTFILOSOFOS];
		for (int i = 0; i < palillos.length; i++) {
			palillos[i] = new Object();
		}
		Object p1, p2;
		for (int i = 0; i < filosofos.length; i++) {
			p1 = palillos[i];
			if(i == filosofos.length -1) {
				p2 = palillos[0];
				filosofos[i] = new Filosofo("f" + (i+1),p2, p1);
			} else {
				p2 = palillos[i+1];
				filosofos[i] = new Filosofo("f" + (i+1),p1, p2);
			}
		}
		for (Filosofo f : filosofos) {
			f.start();
		}
	}
}
