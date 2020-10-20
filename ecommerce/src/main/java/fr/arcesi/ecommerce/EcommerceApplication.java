package fr.arcesi.ecommerce;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import fr.arcesi.ecommerce.dao.CategoryRepository;
import fr.arcesi.ecommerce.dao.ProductRepository;
import fr.arcesi.ecommerce.entities.Category;
import fr.arcesi.ecommerce.entities.Product;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		repositoryRestConfiguration.exposeIdsFor(Category.class);
		for(int i=0;i<10;i++) {
			Category cat=null;
			Random r=new Random();
			int lowCat=1;
			int hightCat=10;
			int resultaCat=r.nextInt(hightCat-lowCat)+lowCat;
			cat=categoryRepository.save(new Category("Categorie"+i, "DescriptionCategorie"+i, "unknown.jpeg"));
			double lowPrice=20;
			double hightPrice=1100;
			for(int a=0;a<20;a++) {
				Random rp=new Random();
				double resultPrice=rp.nextDouble()+hightPrice;
			   productRepository.save(new Product("name"+i, "description"+i, resultPrice , r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), "unknown.jpg", cat));
		   }
		}
		
	
	}

}
