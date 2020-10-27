package fr.arcesi.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.arcesi.ecommerce.entities.Product;
@RepositoryRestResource
//@CrossOrigin("*")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
public interface ProductRepository extends JpaRepository<Product, Long> {

	////http://localhost:8080/products/search/productSelected?page=0&size=5
	@RestResource(path ="productSelected")
	public Page<Product> findByIsSelectedIsTrue(Pageable pageable);

    //http://localhost:8080/products/search/productEnPromotion
	@RestResource(path="productEnPromotion")
	public Page<Product> findByIsPromotionIsTrue(Pageable pageable);
	//http://localhost:8080/products/search/productDisponible
	@RestResource(path = "productDisponible")
	public Page<Product> findByIsAvailableIsTrue(Pageable pageable);
}
