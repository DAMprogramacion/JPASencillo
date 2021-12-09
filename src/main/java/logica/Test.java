package logica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import controladores.ProductController;
import modelo.Product;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductController.setup();
		// ProductController.getAllProducts().
		// forEach(System.out::println);
//		Product product = ProductController.getProductById(115);
//		if (product != null)
//			System.out.println(product);
//		else
//			System.out.println("no existe el producto");
//		ProductController.updatePriceProductById(115, 1.1f);
//		ProductController.removeProductById(115);
		Product product = new Product();
		product.setName("nuevo producto");
		product.setPrices(10);
		ProductController.createProduct(product);
		ProductController.close();
	}

}
