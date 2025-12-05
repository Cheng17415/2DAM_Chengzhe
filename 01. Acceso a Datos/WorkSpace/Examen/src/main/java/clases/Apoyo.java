package clases;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Cliente;
import model.Comercial;
import model.Pedido;

public class Apoyo {
	private EntityManager em;
	
	public Apoyo(EntityManager em) {
		this.em = em;
	}
	
	/********************************************/
	public void visualizarClientes() {
		TypedQuery<Cliente> consulta = em.createNamedQuery("Cliente.findAll", Cliente.class);
		for (Cliente c: consulta.getResultList()) {
			System.out.println(c);
		}
	}
	
	public void visualizarPedidos() {
        TypedQuery<Pedido> consulta = em.createNamedQuery("Pedido.findAll", Pedido.class);
        for (Pedido p : consulta.getResultList()) {
            System.out.println(p);
        }
    }
	
	public void anadirCliente(Cliente c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		System.out.println("Cliente creado con ID " + c.getId());
	}
	
	public void anadirPedido(Pedido p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		System.out.println("Pedido creado con ID " + p.getId());
	}
	public Cliente buscarCliente(int id) {
        return em.find(Cliente.class, id);
    }
	
	public Pedido buscarPedido(int id) {
	        return em.find(Pedido.class, id);
	}
	
	public Comercial buscarComercial(int id) {
		return em.find(Comercial.class, id);
	}
	
	public void actualizarCliente(int id, Cliente cl) {
        EntityTransaction transaccion = em.getTransaction();
        
         transaccion.begin();
            Cliente c = em.find(Cliente.class, id);
            if (c != null) {
                em.merge(cl);
                System.out.println("Pedido actualizado.");
            }
            transaccion.commit();
        
    }
	 public void actualizarPedido(int id, Pedido pe) {
	        EntityTransaction transaccion = em.getTransaction();
	            transaccion.begin();
	            Pedido p = em.find(Pedido.class, id);
	            if (p != null) {
	                em.merge(pe);
	                System.out.println("Pedido actualizado.");
	            }
	            transaccion.commit();
	        
	    }
	 
	public void eliminarCliente(int id) {
		em.getTransaction().begin();
		Cliente c = em.find(Cliente.class, id);
		if(c!=null) {
			em.remove(c);
			System.out.println("Cliente eliminado.");
			
		} else {
			System.out.println("No se ha encontrado al cliente");
		}
		em.getTransaction().commit();
	}
	
	public void eliminarPedido(int id) {
        em.getTransaction().begin();
        Pedido p = em.find(Pedido.class, id);
        if (p != null) {
            em.remove(p);
            System.out.println("Pedido eliminado.");
        } else {
        	System.out.println("No se ha encontrado el pedido");
        }
        em.getTransaction().commit();
    }
}
