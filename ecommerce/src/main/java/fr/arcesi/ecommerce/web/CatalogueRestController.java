package fr.arcesi.ecommerce.web;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.arcesi.ecommerce.dao.CategoryRepository;
import fr.arcesi.ecommerce.dao.ProductRepository;
import fr.arcesi.ecommerce.entities.Category;
import fr.arcesi.ecommerce.entities.Product;
import sun.java2d.opengl.WGLSurfaceData.WGLVSyncOffScreenSurfaceData;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH,RequestMethod.HEAD})
public class CatalogueRestController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryrepository;
	
	@GetMapping(path ="getPhtoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("id") Long id) throws Exception{
		    if(id!=0 && id!=null) {
			Product product=productRepository.findById(id).get();
			if(product.getPhotoName()!=null) {	
			     return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/products/"+product.getPhotoName()));		
			}else {
				return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/products/unknown.jpg"));
			}
		    }else {
		    	return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/products/unknown.jpg"));
		    }
	}
	
	@GetMapping(path = "getPhotoCategory/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageCategorie(@PathVariable("id")Long id) throws Exception {
		Category category=categoryrepository.findById(id).get();
		if(category.getPhotoName()!=null) {
			return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/categories/"+category.getPhotoName()));
		}else {
			return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerce/categories/unknown.jpg"));
		}
	}
	@PostMapping(path = "uploadPhotoProduct/{id}")
	public void uploadPhotoProduct(MultipartFile file,@PathVariable Long id) throws Exception {
		Product p=productRepository.findById(id).get();
		//p.setPhotoName(file.getOriginalFilename());
		 p.setPhotoName(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/ecommerce/products/"+p.getPhotoName()),file.getBytes());
	    productRepository.save(p);
	}
	//Récupérer la photo du categorie
	@PostMapping(path="uploadPhotoCategorie/{idCat}")
	public void uploadPhotoCategorie(MultipartFile file,@PathVariable Long idCat)  throws Exception{
        Category cat=categoryrepository.findById(idCat).get();
        cat.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/ecommerce/categories/"+cat.getPhotoName()),file.getBytes());
	    categoryrepository.save(cat);
	}
	@PostMapping(path = "products/addProduct/{idCat}")
	public Product addProduct(@RequestBody Product newProduct,@PathVariable Long idCat) {
		  Category cat=categoryrepository.findById(idCat).get();
		  if(cat==null) {
			   new ResponseEntity<Category>(cat,HttpStatus.NOT_FOUND);
		  }
		newProduct.setCategory(cat);
		return productRepository.save(newProduct);
	}
	@PutMapping(path = "products/updateProduct/{idProduct}/{idCat}")
	public Product updataProduct(@RequestBody Product currentProduct,@PathVariable("idProduct") Long idProduct, @PathVariable("idCat") Long idCat) {
		Category cat=categoryrepository.findById(idCat).get();
		if(cat==null) {
			new ResponseEntity<Category>(cat,HttpStatus.NOT_FOUND);
		}
		Product productInDataBase=productRepository.findById(idProduct).get();
		Product productSavedInDataBase=productRepository.findById(idProduct).get();
		  if(productInDataBase==null) {
			  new ResponseEntity<Product>(productInDataBase,HttpStatus.NOT_FOUND);
		  }
		 currentProduct.setCode(productInDataBase.getCode());
		 currentProduct.setCategory(cat);
		  System.out.println("tibari"+currentProduct.toString());
		return productRepository.saveAndFlush(currentProduct);
	}
}
