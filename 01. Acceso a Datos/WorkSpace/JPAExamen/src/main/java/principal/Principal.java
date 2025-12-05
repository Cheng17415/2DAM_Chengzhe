package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Cliente;
import model.Comercial;
import model.Pedido;
public class Principal {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamen");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		/*
		//CREATE CLIENTE
		//em.persist(new Cliente(1, "Paco", "Alcaraz", "Cabrera", "Madrid", 10));
		Cliente c1 = new Cliente(2, "Ernesto", "Perez");
		c1.setApellido2("Puebla");
		c1.setCiudad("Segovia");
		c1.setCategoria(20);
		Cliente c2 = new Cliente(3, "Maria", "Trovan");
		c2.setApellido2("Tambor");
		c2.setCiudad("Barcelona");
		c2.setCategoria(30);
		em.merge(c1);
		em.merge(c2);
		
		//CREATE COMERCIAL
		Comercial co1 = new Comercial(1,"Cheng", "Li","Li", "Madrid", 10);
		Comercial co2 = new Comercial(2,"Cheng2", "Li","Li", "Madrid", 20);
		em.merge(co1);
		em.merge(co2);
		
		//CREATE PEDIDO
		Pedido p1 = new Pedido(1,5, c1, co1);
		Pedido p2 = new Pedido(2,10, c2, co1);
		em.merge(p1);
		em.persist(p2);*/
		
		//LEER CLIENTES
		listarClientes(em);
		
		//LEER PEDIDOS
		listarPedidos(em);
		
		transaccion.commit();
		em.close();
		emf.close();
	}
	
	//Lo pondré en otra clase luego.
	public static void crearCliente(EntityManager em, Cliente c) {
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		em.persist(c);
		transaccion.commit();
		System.out.println("Cliente creado " + c.getNombre());
	}
	
	public static Cliente buscarCliente(EntityManager em, int id) {
		return em.find(Cliente.class, id);
	}
	
	public static void listarClientes(EntityManager em) {
		//LISTAR CLIENTES
		TypedQuery<Cliente> clientes = (TypedQuery<Cliente>) em.createNamedQuery("Cliente.findAll");
		for (Cliente cliente : clientes.getResultList()) {
			System.out.println(cliente);
		}
	}
	
	public void actualizarCliente(EntityManager em, int id, String nuevaCiudad, int nuevaCategoria) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente c = em.find(Cliente.class, id);
            if (c != null) {
                c.setCiudad(nuevaCiudad);
                c.setCategoria(nuevaCategoria);
                // Al estar dentro de una transacción y ser un objeto "managed",
                // el commit guardará los cambios automáticamente. No hace falta merge explícito aquí.
                System.out.println("Cliente actualizado.");
            } else {
                System.out.println("Cliente no encontrado para actualizar.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
	
	public static void eliminarCliente(EntityManager em, int id) {
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		Cliente cliente = buscarCliente(em, id);
		
		if(cliente != null) {
			em.remove(cliente);
			System.out.println("Cliente eliminado");
		}
		transaccion.commit();
	}
	
	public static void crearPedido(EntityManager em, Pedido p) {
        EntityTransaction transaccion = em.getTransaction();
        transaccion.begin();
        em.persist(p);
        transaccion.commit();
        System.out.println("Pedido creado con ID: " + p.getId());
        
    }
	
	public static Pedido buscarPedido(EntityManager em, int id) {
		return em.find(Pedido.class, id);
	}
	
	public static void listarPedidos(EntityManager em) {
		//LISTAR CLIENTES
		TypedQuery<Pedido> pedidos = (TypedQuery<Pedido>) em.createNamedQuery("Pedido.findAll");
		for (Pedido pedido : pedidos.getResultList()) {
			System.out.println(pedido);
		}
	}
	
	// UPDATE
    public void actualizarPedido(EntityManager em, int id, double nuevaCantidad) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido p = em.find(Pedido.class, id);
            if (p != null) {
                p.setCantidad(nuevaCantidad);
                System.out.println("Pedido actualizado.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
	public void eliminarPedido(EntityManager em, int id) {
        EntityTransaction transaccion = em.getTransaction();
        transaccion.begin();
        Pedido p = em.find(Pedido.class, id);
        if (p != null) {
            em.remove(p);
            System.out.println("Pedido eliminado.");
        }
        transaccion.commit();
    }

}
/*package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cliente;
import model.Comercial;
import model.Pedido;

public class Principal {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamen");
        EntityManager em = emf.createEntityManager();
        
        GestorDatos gestor = new GestorDatos(em);

        System.out.println("=== INICIO DE OPERACIONES ===");

        // --- PREPARACIÓN: Crear un Comercial (necesario para los pedidos) ---
        Comercial comercial = new Comercial(1, "Cheng", "Li", "Li", "Madrid", 10.5f);
        gestor.crearComercial(comercial);

        // ==========================
        // CRUD CLIENTE
        // ==========================
        
        // 1. CREATE
        System.out.println("\n--- Creando Clientes ---");
        Cliente c1 = new Cliente(1, "Paco", "Alcaraz");
        c1.setCiudad("Madrid");
        c1.setCategoria(10);
        
        Cliente c2 = new Cliente(2, "Maria", "Trovan");
        c2.setCiudad("Barcelona");
        
        gestor.crearCliente(c1);
        gestor.crearCliente(c2);

        // 2. READ
        System.out.println("\n--- Leyendo Cliente ID 1 ---");
        Cliente clienteEncontrado = gestor.buscarCliente(1);
        System.out.println(clienteEncontrado);

        System.out.println("\n--- Listando todos los Clientes ---");
        gestor.listarClientes();

        // 3. UPDATE
        System.out.println("\n--- Actualizando Cliente ID 1 ---");
        gestor.actualizarCliente(1, "Sevilla", 50); // Cambiamos ciudad y categoría

        // 4. DELETE
        // Nota: No borramos aún para poder asignarle pedidos en el siguiente paso

        // ==========================
        // CRUD PEDIDO
        // ==========================

        // 1. CREATE
        System.out.println("\n--- Creando Pedidos ---");
        // Necesitamos recuperar las entidades completas para asignarlas
        Cliente clienteParaPedido = gestor.buscarCliente(1);
        Comercial comercialParaPedido = em.find(Comercial.class, 1);
        
        if (clienteParaPedido != null && comercialParaPedido != null) {
            Pedido p1 = new Pedido(100, 500.0, clienteParaPedido, comercialParaPedido);
            gestor.crearPedido(p1);
        }

        // 2. READ
        System.out.println("\n--- Buscando Pedido ID 100 ---");
        Pedido pedidoEncontrado = gestor.buscarPedido(100);
        System.out.println(pedidoEncontrado);

        // 3. UPDATE
        System.out.println("\n--- Actualizando cantidad Pedido ID 100 ---");
        gestor.actualizarPedido(100, 999.99);

        // 4. DELETE
        System.out.println("\n--- Borrando Pedido ID 100 ---");
        gestor.borrarPedido(100);

        // Borramos el cliente al final para demostrar el CRUD completo
        System.out.println("\n--- Borrando Cliente ID 2 ---");
        gestor.borrarCliente(2);

        System.out.println("\n=== FIN DE OPERACIONES ===");
        
        em.close();
        emf.close();
    }
}*/



/*package principal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Cliente;
import model.Comercial;
import model.Pedido;

public class GestorDatos {

    private EntityManager em;

    public GestorDatos(EntityManager em) {
        this.em = em;
    }

    // ==========================================
    // OPERACIONES CRUD: CLIENTE
    // ==========================================

    // CREATE
    public void crearCliente(Cliente c) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(c);
            tx.commit();
            System.out.println("Cliente creado: " + c.getNombre());
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // READ (Uno) - Usamos find() que es mucho más eficiente que recorrer una lista
    public Cliente buscarCliente(int id) {
        return em.find(Cliente.class, id);
    }

    // READ (Todos)
    public void listarClientes() {
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAll", Cliente.class);
        List<Cliente> lista = query.getResultList();
        for (Cliente c : lista) {
            System.out.println(c);
        }
    }

    // UPDATE
    public void actualizarCliente(int id, String nuevaCiudad, int nuevaCategoria) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente c = em.find(Cliente.class, id);
            if (c != null) {
                c.setCiudad(nuevaCiudad);
                c.setCategoria(nuevaCategoria);
                // Al estar dentro de una transacción y ser un objeto "managed",
                // el commit guardará los cambios automáticamente. No hace falta merge explícito aquí.
                System.out.println("Cliente actualizado.");
            } else {
                System.out.println("Cliente no encontrado para actualizar.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // DELETE
    public void borrarCliente(int id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente c = em.find(Cliente.class, id);
            if (c != null) {
                em.remove(c);
                System.out.println("Cliente eliminado.");
            } else {
                System.out.println("Cliente no encontrado para borrar.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error al borrar cliente (posiblemente tiene pedidos asociados).");
        }
    }

    // ==========================================
    // OPERACIONES CRUD: PEDIDO
    // ==========================================

    // CREATE
    public void crearPedido(Pedido p) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
            System.out.println("Pedido creado con ID: " + p.getId());
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // READ
    public Pedido buscarPedido(int id) {
        return em.find(Pedido.class, id);
    }

    public void listarPedidos() {
        TypedQuery<Pedido> query = em.createNamedQuery("Pedido.findAll", Pedido.class);
        List<Pedido> lista = query.getResultList();
        for (Pedido p : lista) {
            System.out.println(p);
        }
    }

    // UPDATE
    public void actualizarPedido(int id, double nuevaCantidad) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido p = em.find(Pedido.class, id);
            if (p != null) {
                p.setCantidad(nuevaCantidad);
                System.out.println("Pedido actualizado.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // DELETE
    public void borrarPedido(int id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido p = em.find(Pedido.class, id);
            if (p != null) {
                em.remove(p);
                System.out.println("Pedido eliminado.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
    
    // Helper para el comercial del ejemplo
    public void crearComercial(Comercial c) {
        EntityTransaction tx = em.getTransaction();
        try {
             tx.begin();
             em.merge(c); // Usamos merge por si ya existe
             tx.commit();
        } catch (Exception e) {
             if (tx.isActive()) tx.rollback();
        }
    }
}*/
