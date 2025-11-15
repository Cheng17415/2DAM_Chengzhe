package ejercicio2;

import java.util.ArrayList;
public class Ejercicio2 {
	/*
    Crear un programa que elija al azar 15 cartas de la baraja española (15
    objetos de la clase Carta). Usa un objeto de la clase ArrayList para
    almacenarlas y asegúrate que no se repita ninguna.
     */
	
    public static void main(String[] args) {
        ArrayList<Carta> cartas_totales = new ArrayList<>();
        ArrayList<Carta> cartas_random = new ArrayList<>();

        inicializarCartas(cartas_totales);

        //Generar 15 cartas aleatorias sin repetirse
        cartasRandom(cartas_totales, cartas_random);

        System.out.println("Las cartas generadas son:");
        recorrerLista(cartas_random);

    }

    public static void inicializarCartas(ArrayList<Carta> cartas_totales) {
        String[] palos_disp = {"oro", "copa", "espada", "basto"};
        for (int i = 0; i < palos_disp.length; i++) {
            for (int j = 1; j <= 10; j++) {         
                cartas_totales.add(new Carta(palos_disp[i], j));
            }
        }
    }
    

    public static void cartasRandom(ArrayList<Carta> cartas_totales, ArrayList<Carta> cartas_random) {
        for (int i = 0; i < 15; i++) {
            Carta carta = cartas_totales.get(alea(0, cartas_totales.size() - 1));
            cartas_random.add(carta);
            cartas_totales.remove(carta);
        }
    }

    public static void recorrerLista(ArrayList<Carta> cartas_random) {
        for (int i = 0; i < cartas_random.size(); i++) {
            System.out.println(i + 1 + ". " + cartas_random.get(i));
        }
    }

    public static int alea(int li, int ls) {
        return (int) (Math.random() * (ls - li + 1)) + li;
    }
}

