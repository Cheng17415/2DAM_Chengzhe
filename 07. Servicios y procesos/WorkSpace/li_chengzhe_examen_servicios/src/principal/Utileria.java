package principal;

public class Utileria {
	static boolean sonDigitos(String cadena) {
		for(int i= 0; i < cadena.length(); i++) {
			if(!Character.isDigit(cadena.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
