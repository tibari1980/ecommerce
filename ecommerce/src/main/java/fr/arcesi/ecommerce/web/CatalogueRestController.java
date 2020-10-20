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

import fr.arcesi.ecommerce.dao.ProductRepository;
import fr.arcesi.ecommerce.entities.Product;

@RestController
@CrossOrigin("*")
public class CatalogueRestController {

	@Autowired
	private ProductRepository productRepository;

	
	@GetMapping(path ="getPhtoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("id") Long id) throws Exception{
		Product product=productRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/products/"+product.getPhotoName()));
	}
	
	@PostMapping(path = "uploadPhotoProduct/{id}")
	public void uploadPhotoProduct(MultipartFile file,@PathVariable Long id) throws Exception {
		Product p=productRepository.findById(id).get();
		p.setPhotoName(file.getOriginalFilename());
		System.out.println("tibari"+file.getOriginalFilename());
		//p.setPhotoName(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/ecommerce/products/"+p.getPhotoName()),file.getBytes());
	    productRepository.save(p);
	}
}
