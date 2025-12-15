package Solucion;

import java.util.ArrayList;
import java.util.List;

public class Supermercado {
	private List<Cliente> cola =null;
    private int  nclientes;
	
    public Supermercado(List<Cliente> clientes) {
		super();
		this.cola =  clientes;
		this.nclientes=clientes.size();
	}
    public void procesar() throws InterruptedException {
    	long inicio = System.currentTimeMillis();
        Cajera[] cajeras=new Cajera[2];
        for(int i=0;i< cajeras.length;i++)cajeras[i]=new Cajera("Cajera-"+(i+1), cola, inicio);
        for(int i=0;i< cajeras.length;i++)cajeras[i].start();

        int v=0;
        while(v!=cajeras.length) {
            v = 0;
            for (int i = 0; i < cajeras.length; i++) {
                if (cajeras[i].isAlive()) {
                    break;
                } else {
                    v++;
                }
            }
        }

        long total = System.currentTimeMillis() - inicio;
        System.out.printf("[%6d ms] Sistema: clientes atendidos = %d, tiempo total = %d ms%n",
                total, nclientes-cola.size(), total);

    }

public static void main(String[] args) throws InterruptedException {
        
        // Crear clientes
        List<Cliente> clientes = new ArrayList<Cliente>();
            clientes.add( new Cliente("Ana", List.of(500, 800, 400, 700)));
            clientes.add(new Cliente("Luis", List.of(300, 300, 300)));
            clientes.add(new Cliente("Mar", List.of(1000, 200, 200, 500)));
            clientes.add(new Cliente("Iván", List.of(600, 600)));
            clientes.add(new Cliente("Sofía", List.of(200, 900, 300)));
            clientes.add( new Cliente("Ana1", List.of(500, 800, 400, 700)));
            clientes.add(new Cliente("Luis1", List.of(300, 300, 300)));
            clientes.add(new Cliente("Mar1", List.of(1000, 200, 200, 500)));
            clientes.add(new Cliente("Iván1", List.of(600, 600)));
            clientes.add(new Cliente("Sofía1", List.of(200, 900, 300)));
     

       Supermercado mercadona=new Supermercado(clientes);       
       mercadona.procesar();
        
       
    }
}
