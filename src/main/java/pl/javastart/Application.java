package pl.javastart;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.javastart.dao.ProductDao;
import pl.javastart.model.Product;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
			
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		ProductDao productDao = ctx.getBean(ProductDao.class);
		
		List<Product> products = new ArrayList<>();
		products.add(new Product("Telewizor", "Samsung", 4500.0));
	    products.add(new Product("Opiekacz", "Opiex", 120.0));
	    products.add(new Product("Laptop", "Samsung", 3599.0));
	    products.add(new Product("Kino domowe", "Yamaha", 2600.0));
	    products.add(new Product("Smartfon", "Sony", 2100.0));
		
		products.forEach(productDao::save);
	    
		System.out.println("Product Telewizor:");
        List<Product> tvProducts = productDao.getByName("Telewizor");
        tvProducts.forEach(System.out::println);
        
	
        productDao.delByProducer("Samsung");
        
        System.out.println("Lista produktów po usunieciu Samsunga");
        List<Product> allProducts = productDao.getAll();
        allProducts.forEach(System.out::println);
        
        
        ctx.close();
	}

}
