package principal;

import java.util.ArrayList;
import java.util.Scanner;

import clases.Contacto;
import clases.Mensaje;

public class Principal {

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Contacto> contactos = new ArrayList<>();
	private static ArrayList<Mensaje> mensajes = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.print("Your name: ");
		String nombre = sc.nextLine();
		System.out.println("Hello " + nombre);
		menuPrincipal();
	}
	
	public static void menuPrincipal() {
	    String[] opciones = {"1. Manage contacts", "2. Messages", "3. Quit"};
	    int num;

	    while (true) {
	        int eleccion = menu(opciones);

	        // ---- CONTACTOS ----
	        if (eleccion == 1) {
	            String[] cOpciones = {
	                "1. Show all contacts",
	                "2. Add a new contact",
	                "3. Search for a contact",
	                "4. Delete a contact",
	                "5. Go back to the previous menu"
	            };

	            int cEleccion = 0;
	            while (cEleccion != 5) {
	                cEleccion = menu(cOpciones);

	                switch (cEleccion) {
	                    case 1:
	                        showAllContacts();
	                        break;
	                    case 2:
	                        System.out.print("Phone number: ");
	                        num = Integer.parseInt(sc.nextLine());
	                        addContact(num);
	                        break;
	                    case 3:
	                        System.out.print("Number to search: ");
	                        num = Integer.parseInt(sc.nextLine());
	                        Contacto c = searchContact(num);
	                        if (c == null) System.out.println("No se encontró");
	                        else System.out.println(c);
	                        break;
	                    case 4:
	                        System.out.print("Number to delete: ");
	                        num = Integer.parseInt(sc.nextLine());
	                        deleteContact(num);
	                        break;
	                    case 5:
	                        System.out.println("Returning to the previous menu...");
	                        break;
	                    default:
	                        System.out.println("Opción no válida");
	                }
	            }

	        // ---- MENSAJES ----
	        } else if (eleccion == 2) {

	            String[] mOpciones = {
	                "1. See the list of all messages",
	                "2. Send a new message",
	                "3. Go back to the previous menu"
	            };

	            int mEleccion = 0;
	            while (mEleccion != 3) {
	                mEleccion = menu(mOpciones);

	                switch (mEleccion) {
	                    case 1:
	                        showAllMessages();
	                        break;
	                    case 2:
	                        sendMessage();
	                        break;
	                    case 3:
	                        System.out.println("Returning to the previous menu...");
	                        break;
	                    default:
	                        System.out.println("Opción no válida");
	                }
	            }

	        // ---- SALIR ----
	        } else if (eleccion == 3) {
	            return;
	        } else {
	            System.out.println("Opción no válida");
	        }
	    }
	}

	
	private static void sendMessage() {
		showAllContacts();
		System.out.println("Send message to which number?");
		int number = Integer.parseInt(sc.nextLine());
		Contacto contacto = searchContact(number);
		if(contacto == null) {
			System.out.println("No se ha encontrado esa persona");
			return;
		}
		
		System.out.println("Message: ");
		String message = sc.nextLine();
		Mensaje m = messagedBefore(contacto); 
		if(m == null) {
			m = new Mensaje(contacto, message);
			mensajes.add(m);
		}
		m.enviarMensaje(message);
	}
	
	private static Mensaje messagedBefore(Contacto c) {
		for (Mensaje m : mensajes) if (m.getContacto().equals(c)) return m;
		return null;
	}

	private static void showAllMessages() {
		for (Mensaje mensaje : mensajes) {
			System.out.println(mensaje);
		}
	}

	private static void deleteContact(int num) {
		Contacto c = searchContact(num);
		if(c != null) {
			contactos.remove(c);
			System.out.println("Eliminado con éxito");
			return;
		}
		System.out.println("No se pudo encontrar ese contacto");
	}

	private static void addContact(int num) {
		if(searchContact(num) != null) {
			System.out.println("Ese contacto ya existe");
			return;
		}
		System.out.print("Name: ");
		String name = sc.nextLine();
		contactos.add(new Contacto(name,num));
	}
	
	private static Contacto searchContact(int num) {
		for (Contacto contacto : contactos) {
			if(contacto.getNumero() == num) return contacto;
		}
		return null;
	}
	
	private static void showAllContacts() {
		for (Contacto contacto : contactos) {
			System.out.println(contacto);
		}
	}

	public static int menu(String[] opciones) {
		int eleccion = -1;
		try {
			while(eleccion < 1 || eleccion > opciones.length) {	
				for (String opcion : opciones) {
					System.out.println(opcion);
				}
				eleccion = Integer.parseInt(sc.nextLine());
			}
		} catch(NumberFormatException e) {
			System.out.println("Introduzca un número válido");
		}
		return eleccion;
		}
		
	}
