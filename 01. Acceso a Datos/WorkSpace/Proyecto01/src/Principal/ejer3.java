package primero;

import java.util.Scanner;

public class ejer3 {

	Scanner scanner = new Scanner(System.in);
	public static int alea(int li, int ls) {
		return (int)Math.random() *(ls-li+1)+li;
	}
	
	public static int decena(int n) {
		return (int)((n-1)/10)+1;
	}
	
	
	public static void main(String[] args) {
		int nu;
		for (int i = 0; i <= 25; i++) {
			nu=alea(100,500);
			System.out.println(alea(100,500));
			//System.out.println("El numero %d pertenece a la %d decena /n", nu,decena(nu));
		}
				
	}
	

}
