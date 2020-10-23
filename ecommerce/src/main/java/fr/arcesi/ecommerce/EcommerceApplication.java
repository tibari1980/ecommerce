package fr.arcesi.ecommerce;

import java.util.Arrays;
import java.util.List;
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
		List<Category> lstCategories=Arrays.asList(new Category("Créme visage","créme visage ",null),
				                                   new Category("Anti-âge", "anti-age", null),
				                                    new Category("Serum et huile", "Serum et huile",null),
				                                    new Category("Soin yeux","soin yeux",null),
				                                    new Category("Nettoyant et démaquillant","nettoyant et démaquillant",null),
				                                    new Category("Masque","masque",null),
				                                    new Category("Hydratation","hydratation",null),
				                                    new Category("Purifiant","purifiant",null),
				                                    new Category("Créme main","créme main",null),
	    	                                    new Category("Savons","savons description",null));
		lstCategories.stream().forEach(cat->{
			Random r=new Random();
			int lowCat=1;
			int hightCat=10;
			int resultaCat=r.nextInt(hightCat-lowCat)+lowCat;
			cat=categoryRepository.save(cat);
			double lowPrice=20;
			double hightPrice=1100;
			for(int a=0;a<20;a++) {
				Random rp=new Random();
				double resultPrice=rp.nextDouble()+hightPrice;
			   productRepository.save(new Product("name"+cat.getCode(), "description"+cat.getCode(), resultPrice , r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), "unknown.jpg", cat));
		   }
		

		});
					
	
	}

}
