package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Product;

public class ProductController {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASencillo");
	private static EntityManager em = null;

	public static void setup() {
		if (em == null)
			em = emf.createEntityManager();
	}

	public static void close() {
		if (em != null)
			em.close();
	}

	public static List<Product> getAllProducts() {
		Query query = em.createNamedQuery("Product.findAll");
		return query.getResultList();
	}

	public static Product getProductById(int id) {
		Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id");
		query.setParameter("id", id);
		try {
			return (Product) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public static void updatePriceProductById(int id, float newPrices) {
		Product product = getProductById(id);
		if (product == null) {
			System.out.println("no existe el producto");
			return;
		}
		product.setPrices(newPrices);
		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();
		System.out.println("Producto actualizado");
	}

	public static void removeProductById(int id) {
		Product product = getProductById(id);
		if (product == null) {
			System.out.println("no existe el producto");
			return;
		}
		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
		System.out.println("Producto borrado");
	}

	public static void createProduct(Product newProduct) {
		em.getTransaction().begin();
		em.persist(newProduct);
		em.getTransaction().commit();
		System.out.println("Producto creado");
	}

}
