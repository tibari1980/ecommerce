package fr.arcesi.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.arcesi.ecommerce.entities.Category;

@RepositoryRestResource
@CrossOrigin("*")
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
