package fr.arcesi.ecommerce.web;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.arcesi.ecommerce.dao.CategoryRepository;
import fr.arcesi.ecommerce.dao.ProductRepository;
import fr.arcesi.ecommerce.entities.Category;
import fr.arcesi.ecommerce.entities.Product;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CatalogueRestController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryrepository;
	
	@GetMapping(path ="getPhtoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("id") Long id) throws Exception{
		Product product=productRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/products/"+product.getPhotoName()));
	}
	
	@PostMapping(path = "uploadPhotoProduct/{id}")
	public void uploadPhotoProduct(MultipartFile file,@PathVariable Long id) throws Exception {
		Product p=productRepository.findById(id).get();
		p.setPhotoName(file.getOriginalFilename());
		//p.setPhotoName(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/ecommerce/products/"+p.getPhotoName()),file.getBytes());
	    productRepository.save(p);
	}
	
	@PostMapping(path="uploadPhotoCategorie/{idCat}")
	public void uploadPhotoCategorie(MultipartFile file,@PathVariable Long idCat)  throws Exception{
        Category cat=categoryrepository.findById(idCat).get();
        cat.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/ecommerce/categories/"+cat.getPhotoName()),file.getBytes());
	    categoryrepository.save(cat);
	}
}
