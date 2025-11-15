package ficherosPiramides;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FicherosPiramides {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException  {
		//generarTriangulo();
		//generarTrianguloConPocaSentencia();
		//piramideInvertida();
		//piramideInvertidaProfe();
		//piramideDeLado();
		piramideDeOtroLado();
	}

	public static void contarPalabras() {
		String rutaArchivo = "ruta/del/archivo.txt"; 
        int contadorPalabras = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.trim().split("\\s+");
                contadorPalabras += palabras.length;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("El archivo contiene " + contadorPalabras + " palabras.");
    }
	
	public static void piramideDeOtroLado() {
		System.out.print("Ingrese el número de niveles de la pirámide de lado: ");
        int niveles = sc.nextInt();
        for (int i = 1; i <= niveles; i++) { 
        	System.out.println(" ".repeat(niveles-1)+ "*".repeat(i));
        }
        for (int i = niveles; i >= 1; i--) { 
        	System.out.println(" ".repeat(niveles-1)+ "*".repeat(i));
        }
	}
	
	public static void piramideDeLado() {
		System.out.print("Ingrese el número de niveles de la pirámide de lado: ");
        int niveles = sc.nextInt();
        for (int i = 1; i <= niveles; i++) { 
        	System.out.println("*".repeat((i)));
        }
        for (int i = niveles-1; i >= 1; i--) { 
        	System.out.println("*".repeat((i)));
        }
	}
	
	public static void piramideInvertidaProfe() throws IOException {
		System.out.print("Ingrese el número de niveles de la pirámide invertida: ");
        int niveles = sc.nextInt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("piramideNuevo.txt"))) {
        	for (int i = 1; i <= niveles; i++) { 
                System.out.println(" ".repeat(i - 1)+ "*".repeat((2*niveles)- (2 * (i - 1)+1)));
                bw.newLine();
            }
        }
	}
	
	public static void generarTrianguloConPocaSentencia() throws IOException {
		System.out.print("Ingrese el número de niveles de la pirámide: ");
        int niveles = sc.nextInt();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("piramideNuevo.txt"))) {
            for (int i = 1; i <= niveles; i++) { 
                System.out.println(" ".repeat(i - niveles)+ "*".repeat(2* (i - 1)+1));
                bw.newLine();
            }
        }
    }
		
	public static void piramideInvertida() throws IOException {
		System.out.print("Ingrese el número de niveles de la pirámide invertida: ");
        int niveles = sc.nextInt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("piramideNuevo.txt"))) {
            for (int i = niveles; i >= 1; i--) { 
                System.out.println(" ".repeat(niveles - i)+ "*".repeat(2 * (i - 1)+1));
                bw.newLine();
            }
        }
	}
	//funcion para poder utilizar repeat que nos devuelve cadena
	public static String repetir(String cadena, int n) {
		String  ca="";
		for (int i = 0; i <=n; i++) {
			ca +=cadena;
		}
		return ca;
	}
	public static void generarTriangulo() throws IOException{
		System.out.print("Ingrese el nro de niveles de la pirámide: ");
        int niveles = sc.nextInt();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("piramide.txt"))) {
            for (int i = 1; i <= niveles; i++) { 
                for (int j = 1; j <= niveles - i; j++) {
                    bw.write(" ");
                }

                for (int k = 1; k <= (2 * i - 1); k++) {
                    bw.write("°"); 
                }
                
                bw.newLine();
            }
        }

        System.out.println("La pirámide ha sido guardada en el archivo.");
		}
	}

