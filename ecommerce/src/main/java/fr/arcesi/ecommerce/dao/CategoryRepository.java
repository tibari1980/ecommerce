package fr.arcesi.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.arcesi.ecommerce.entities.Category;

@RepositoryRestResource
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
